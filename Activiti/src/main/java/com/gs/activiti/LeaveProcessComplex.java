package com.gs.activiti;

import com.gs.bean.LeaveApply;
import com.gs.bean.User;
import junit.framework.TestCase;
import org.activiti.engine.*;
import org.activiti.engine.impl.cfg.StandaloneProcessEngineConfiguration;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.junit.Test;

import java.util.*;

public class LeaveProcessComplex extends TestCase {

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
        deploy();
    }

    @Test
    public void testStartLeaveProcess() {
        startProcess(new User("user2", "emp"));
    }

    @Test
    public void testLeaveSubmit() {
        List<Task> taskList = listSubmitTasks(new User("user2", "emp"));
        LeaveApply leaveApply = new LeaveApply("张三", 10, "回家", Calendar.getInstance().getTime());
        if (taskList != null && taskList.size() > 0) {
            for (Task task : taskList) {
                if (task.getName().equals("员工提交请假条")) {
                    executeSubmitTask(task.getId(), leaveApply);
                }
            }
        }
    }

    @Test
    public void testLeaveCheck() {
        List<Task> taskList = listCheckTasks(new User("boss3", "manager"));
        if (taskList != null && taskList.size() > 0) {
            for (Task task : taskList) {
                if (task.getName().equals("经理审核")) {
                    executeCheckTask(task.getId());
                }
            }
        }
    }

    /**
     * 部署流程
     */
    public void deploy() {
        RepositoryService repositoryService = processEngine.getRepositoryService();
        Deployment deployment = repositoryService.createDeployment().addClasspathResource("bpmn/leave_process_complex.bpmn20.xml").deploy(); // 去读取src根目录下的流程定义文件并部署到activiti中
        System.out.println(deployment.getId() + deployment.getKey() + deployment.getName());
    }

    /**
     * 由指定用户启动流程
     */
    public void startProcess(User user) {
        RuntimeService runtimeService = processEngine.getRuntimeService();
        IdentityService identityService = processEngine.getIdentityService();
        // 设置谁启动流程，这个值会传递到流程定义的activiti:initiator指定的变量中
        // 在流程定义中，可以使用${} el表达式获取此参数值
        identityService.setAuthenticatedUserId(user.getName());
        ProcessInstance processInstance = runtimeService.startProcessInstanceByKey("leave_process_complex");
        System.out.println(processInstance.getName());
    }

    /**
     * 列出指定用户的待办任务
     * @param user
     * @return
     */
    public List<Task> listSubmitTasks(User user) {
        TaskService taskService = processEngine.getTaskService();
        List<Task> taskList = taskService.createTaskQuery().taskAssignee(user.getName()).list(); // 只能通过指定的单个名字来查找任务，assignee指派者，必须是单个
        for (Task task : taskList) {
            System.out.println(task.getId() + ", " + task.getName());
        }
        return taskList;
    }

    public List<Task> listCheckTasks(User user) {
        TaskService taskService = processEngine.getTaskService();
        List<Task> taskList = taskService.createTaskQuery().taskCandidateUser(user.getName()).list(); // 从候选人中去查找任务
        // List<Task taskList1 = taskService.createTaskQuery().taskCandidateGroup(user.getRole.getName());
        for (Task task : taskList) {
            // 获取上一个任务传递过来的数据
            LeaveApply leaveApply = (LeaveApply) taskService.getVariable(task.getId(), "leaveApply");
            System.out.println(task.getId() + ", " + task.getName());
            System.out.println(leaveApply);
        }
        return taskList;
    }

    /**
     * 用户去完成指定编号的任务
     * @param taskId
     */
    public void executeSubmitTask(String taskId, LeaveApply leaveApply) {
        TaskService taskService = processEngine.getTaskService();
        Map<String, Object> data = new HashMap<>();
        data.put("leaveApply", leaveApply);
        taskService.complete(taskId, data); // 把数据传递到下一个流程任务
    }

    public void executeCheckTask(String taskId) {
        TaskService taskService = processEngine.getTaskService();
        taskService.complete(taskId);
    }

}
