package com.gs.activiti;

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
import java.util.List;
import java.util.zip.ZipInputStream;

/**
 * Activiti的数据存储全部在数据库中
 *
 * Activiti的核心是ProcessEngine类，流程引擎类，Activiti的所有功能的正常运行都是基于ProcessEngine
 * 首先要获取流程引擎对象，需要借助ProcessEngineConfiguration对象
 * 在构建ProcessEngine对象的时候，会创建28张activiti的表，此28张表用于存储activiti的一切数据，包括表单数据，
 * 流程定义数据，历史记录记录，任务数据，用户身份数组
 *
 * 28张表都以act_为前缀，把表进行了分类
 * act_evt_log: Event 事件日志表
 * act_ge: General 通用数据表
 * act_hi: history 历史记录表
 *  actinst: 活动实例记录表
 *  attachment: 附近记录表
 *  comment：评论记录表
 *  detail: 详情表
 *  identitylink: 用户记录表  哪个用户执行哪个任务
 *  procinst: 流程实例记录表
 *  taskinst:  任务实例记录表
 *  varinst:  变量实例记录表
 * act_id: identity 用户身份表，存储用户或用户组（角色）
 * act_precdef_info: 流程定义记录表
 * act_re: repository 仓库，用于存储流程相关的所有静态资源，如xml文档，图片
 *  deployment: 流程部署记录表
 *  model: 流程模型
 *  procdef: 流程定义记录表
 * act_ru: runtime 流程运行时数据表
 *
 * act_ge_bytearray:
 * act_procdef_info
 * act_re_deployment
 * act_re_procdef
 * act_ru_task
 * act_ru_varibale
 *
 * Activiti提供的API服务
 * RepositoryService  静态资源的服务接口
 * IdentityService   用户和用户组的服务接口
 * RuntimeService    运行时服务接口
 * TaskService       任务服务接口
 * HistoryService    历史记录服务接口
 * FormService       表单服务接口
 */
public class ActivitiTest extends TestCase {

    private ProcessEngine processEngine;

    /**
     * 1、获取核心流程引擎对象
     */
    @Override
    public void setUp() {
        ProcessEngineConfiguration processEngineConfiguration = new StandaloneProcessEngineConfiguration()
                .setJdbcUrl("jdbc:mysql://localhost:3306/d_activiti?useUnicode=true&characterEncoding=utf8")
                .setJdbcUsername("root")
                .setJdbcPassword("123456")
                .setJdbcDriver("com.mysql.jdbc.Driver")
                // 设置Activiti数据库表的操作模式
                // ProcessEngineConfiguration.DB_SCHEMA_UPDATE_TRUE     在获取ProcessEngine对象时，如果表不存在，则自动创建28张数据表，如果表已经存在了，则不创建
                // ProcessEngineConfiguration.DB_SCHEMA_UPDATE_FALSE    在获取ProcessEngine对象时不自动创建28张数据表
                // ProcessEngineConfiguration.DB_SCHEMA_UPDATE_CREATE_DROP  在获取ProcessEngine对象时，如果表不存在，则自动创建28张数据表，如果表已经存在了，则先删除再创建
                .setDatabaseSchemaUpdate(ProcessEngineConfiguration.DB_SCHEMA_UPDATE_TRUE);
        processEngine = processEngineConfiguration.buildProcessEngine(); // 构建流程引擎对象
        String pName = processEngine.getName();
        String ver = ProcessEngine.VERSION;
        System.out.println("ProcessEngine [" + pName + "] Version: [" + ver + "]");
    }

    /**
     * 2、创建流程定义，画流程图
     * 3、流程部署，告诉activiti哪里有流程图和xml文件
     *  act_ge_bytearray
     *  act_re_deployment   对应于Deployment对象
     *  act_re_procdef    流程定义表，保存有流程的名字，key,流程描述信息
     */
    @Test
    public void testDeploy() {
        RepositoryService repositoryService = processEngine.getRepositoryService();
        // Deployment deployment = repositoryService.createDeployment().addClasspathResource("bpmn/leave_process.bpmn20.xml").deploy(); // 去读取src根目录下的流程定义文件并部署到activiti中
        try {
            // 通过zip文件来部署
            Deployment deployment = repositoryService.createDeployment().
                    addZipInputStream(new ZipInputStream(new FileInputStream(
                            new File("src/main/resources/bpmn/goods_apply_process.zip")))).deploy();
            System.out.println(deployment.getId() + deployment.getKey() + deployment.getName());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * 4、启动流程
     *
     * act_hi_actinst   activity实例记录   activity  :事件，任务，边界，网关
     * act_hi_porcinst  流程实例记录
     * act_hi_taskinst  任务实例记录
     *
     * act_ru_execution  流程实例中已经做过的和未做的Activity
     * act_ru_task     当前即将要执行的任务
     * act_ru_variable  当前即将要执行的任务关联的变量数据
     */
    @Test
    public void testStartLeaveProcess() {
        RuntimeService runtimeService = processEngine.getRuntimeService();
        ProcessInstance processInstance = runtimeService.startProcessInstanceByKey("leave_process"); // 根据流程的key来启动流程
        System.out.println(processInstance.getName());
    }

    /**
     * 5、完成（执行）流程任务
     */
    @Test
    public void testLeaveSubmit() {
        // 获取当前要执行的任务
        TaskService taskService = processEngine.getTaskService();
        // 创建任务查询器
        List<Task> taskList = taskService.createTaskQuery().list(); // 通过任务查询器列出所有人的所有任务
        for (Task task : taskList) {
            System.out.println(task.getId() + ", " + task.getName());
            taskService.complete(task.getId()); // 完成任务
        }
    }

    /**
     * 6、结束（执行）流程任务
     */
    @Test
    public void testLeaveCheck() {
        TaskService taskService = processEngine.getTaskService();
        List<Task> taskList = taskService.createTaskQuery().list();
        for (Task task : taskList) {
            System.out.println(task.getId() + ", " + task.getName());
            taskService.complete(task.getId());
        }
    }

}
