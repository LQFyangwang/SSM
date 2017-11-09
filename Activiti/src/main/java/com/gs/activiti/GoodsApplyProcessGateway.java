package com.gs.activiti;

import com.gs.bean.GoodsApply;
import com.gs.bean.LeaveApply;
import com.gs.bean.User;
import junit.framework.TestCase;
import org.activiti.engine.*;
import org.activiti.engine.impl.cfg.StandaloneProcessEngineConfiguration;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.junit.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.zip.ZipInputStream;

public class GoodsApplyProcessGateway extends TestCase {

    private ProcessEngine processEngine;

    @Override
    public void setUp() {
        ProcessEngineConfiguration processEngineConfiguration = new StandaloneProcessEngineConfiguration()
                .setJdbcUrl("jdbc:mysql://localhost:3306/d_activiti?useUnicode=true&characterEncoding=utf8")
                .setJdbcUsername("root")
                .setJdbcPassword("123456")
                .setJdbcDriver("com.mysql.jdbc.Driver")
                .setDatabaseSchemaUpdate(ProcessEngineConfiguration.DB_SCHEMA_UPDATE_TRUE);
        processEngine = processEngineConfiguration.buildProcessEngine(); // 构建流程引擎对象
    }

    @Test
    public void testDeploy() {
        deploy("goods_apply_process_gateway");
    }

    @Test
    public void testStartLeaveProcess() {
        startProcess(new User("张三", "emp"), "goods_apply_process_gateway");
    }

    @Test
    public void testSubmit() {
        List<Task> taskList = listSubmitTasks(new User("张三", "emp"));
        for (Task task : taskList) {
            if (task.getName().equals("提交领用单")) {
                executeSubmitTask(task.getId(), new GoodsApply("电脑", 8,
                        "新招聘了三个员工", Calendar.getInstance().getTime(), Calendar.getInstance().getTime()));
            }
        }
    }

    @Test
    public void testStockCheck() {
        List<Task> taskList = listCheckTasks(new User("stock1", "stock"));
        for (Task task : taskList) {
            executeStockCheckTask(task.getId());
        }
    }

    @Test
    public void testManagerCheck() {
        List<Task> taskList = listCheckTasks(new User("manager1", "manager"));
        for (Task task : taskList) {
            executeManagerCheckTask(task.getId());
        }
    }

    /**
     * 根据流程名来部署流程
     */
    public void deploy(String processName) {
        RepositoryService repositoryService = processEngine.getRepositoryService();
        try {
            repositoryService.createDeployment().addZipInputStream(new ZipInputStream(new FileInputStream(
                    new File("src/main/resources/bpmn/" + processName + ".zip")
            ))).deploy();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * 由指定用户启动流程
     */
    public void startProcess(User user, String processKey) {
        RuntimeService runtimeService = processEngine.getRuntimeService();
        IdentityService identityService = processEngine.getIdentityService();
        identityService.setAuthenticatedUserId(user.getName());
        runtimeService.startProcessInstanceByKey(processKey);
    }

    /**
     * 列出指定用户的待办任务
     * @param user
     * @return
     */
    public List<Task> listSubmitTasks(User user) {
        TaskService taskService = processEngine.getTaskService();
        List<Task> taskList = taskService.createTaskQuery().taskAssignee(user.getName()).list();
        for (Task task : taskList) {
            if (task.getName().equals("提交领用单")) {
                Object obj = taskService.getVariable(task.getId(), "resubmit");
                if (obj != null) {
                    System.out.println((String) obj);
                }
            }
        }
        return taskList;
    }

    public List<Task> listCheckTasks(User user) {
        TaskService taskService = processEngine.getTaskService();
        return taskService.createTaskQuery().taskCandidateGroup(user.getRoleName()).list();
    }

    public void executeSubmitTask(String taskId, GoodsApply goodsApply) {
        TaskService taskService = processEngine.getTaskService();
        Map<String, Object> data = new HashMap<>();
        data.put("goodsApply", goodsApply);
        taskService.complete(taskId, data);
    }

    public void executeStockCheckTask(String taskId) {
        TaskService taskService = processEngine.getTaskService();
        GoodsApply goodsApply = (GoodsApply) taskService.getVariable(taskId, "goodsApply");
        System.out.println(goodsApply);
        Map<String, Object> data = new HashMap<>();
        if (goodsApply.getQuantity() >= 10) {
            data.put("resubmit", "物品申请数不能大于10个,以后记住啦!");
        } else if (goodsApply.getQuantity() >= 5 && goodsApply.getQuantity() < 10) {
            data.put("stock_check", "物品数大于等于5个，小于10个，需要您审核！");
        }
        taskService.complete(taskId, data);
    }

    public void executeManagerCheckTask(String taskId) {
        TaskService taskService = processEngine.getTaskService();
        Object obj = taskService.getVariable(taskId, "stock_check");
        if (obj != null) {
            System.out.println((String) obj);
        }
        taskService.complete(taskId);
    }

}
