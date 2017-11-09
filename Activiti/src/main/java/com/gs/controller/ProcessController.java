package com.gs.controller;

import com.gs.bean.GoodsApply;
import com.gs.bean.Process;
import com.gs.bean.User;
import com.gs.common.FileUtils;
import com.gs.service.ProcessService;
import org.activiti.engine.task.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;

@Controller
@RequestMapping("/process")
public class ProcessController {

    @Autowired
    private ProcessService processService;

    @RequestMapping(value = "save", method = RequestMethod.POST, produces = {"application/json;charset=utf-8"})
    @ResponseBody
    public String save(MultipartFile process) {
        String filename = process.getOriginalFilename();
        String path = FileUtils.getBpmnDir() + "/" + filename;
        try {
            process.transferTo(new File(path));
        } catch (IOException e) {
            e.printStackTrace();
        }
        processService.save(new Process(FileUtils.getNameWithoutSuffix(filename), "uploads/bpmn/" + filename));
        return "上传成功";
    }

    @GetMapping("all")
    public ModelAndView listAll() {
        ModelAndView mav = new ModelAndView("all_process");
        mav.addObject("processes", processService.listAll());
        return mav;
    }

    @GetMapping(value = "deploy/{processName}", produces = {"application/json;charset=utf-8"})
    @ResponseBody
    public String deploy(@PathVariable("processName") String processName) {
        processService.deploy(processName);
        return "部署成功";
    }

    @GetMapping("to_goods_apply_page")
    public String startGoodsApplyPage() {
        return "start_goods_apply";
    }

    @GetMapping("goods_apply_page")
    public ModelAndView goodsApplyPage() {
        User user = new User("user1", "emp");
        ModelAndView mav = new ModelAndView("goods_apply");
        List<Task> taskList = processService.listAssigneeTasks(user);
        for (Task task : taskList) {
            if (task.getName().equals("提交领用单")) {
                mav.addObject("taskId", task.getId());
                return mav;
            }
        }
        processService.startProcess(user, "goods_apply_process_gateway");
        return mav;
    }

    @GetMapping("tasks")
    public ModelAndView allTasks() {
        User user = new User("user1", "emp");
        ModelAndView mav = new ModelAndView("tasks");
        mav.addObject("tasks", processService.listAssigneeTasks(user));
        return mav;
    }

    @GetMapping("stock_check_page")
    public ModelAndView bossCheckPage(){
        User user = new User("stock1", "stock");
        ModelAndView mav = new ModelAndView("check");
        mav.addObject("tasks", processService.listCandidateGroupTasks(user));
        return mav;
    }

    @PostMapping(value = "submit", produces = {"application/json;charset=utf-8"})
    @ResponseBody
    public String submit(String taskId, GoodsApply goodsApply) {
        processService.executeTask(taskId, "goodsApply", goodsApply);
        return "已提交申请";
    }

    @GetMapping(value = "check/{taskId}", produces = {"application/json;charset=utf-8"})
    @ResponseBody
    public String check(@PathVariable("taskId") String taskId) {
        processService.executeTask(taskId, null,null);
        return "审核成功";
    }

    /**
     * 输出流程静态图的方法
     * @param response
     * @return
     */
    @GetMapping("view_proc")
    public String viewProc(HttpServletResponse response) {
        InputStream inputStream = processService.getStaticProcDiagram("goods_apply_process_gateway");
        try {
            OutputStream outputStream = response.getOutputStream();
            byte[] bytes = new byte[1024];
            int count = 0;
            while ((count = inputStream.read(bytes)) != -1) {
                outputStream.write(bytes, 0, count);
            }
            inputStream.close();
            outputStream.flush();
            outputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 输出流程动态图的方法
     * @param response
     * @return
     */
    @GetMapping("view_proc1")
    public String viewProc1(HttpServletResponse response) {
        InputStream inputStream = processService.getDynamicProcDiagram(new User("user1", "emp"), "goods_apply_process_gateway");
        try {
            OutputStream outputStream = response.getOutputStream();
            byte[] bytes = new byte[1024];
            int count = 0;
            while ((count = inputStream.read(bytes)) != -1) {
                outputStream.write(bytes, 0, count);
            }
            inputStream.close();
            outputStream.flush();
            outputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

}
