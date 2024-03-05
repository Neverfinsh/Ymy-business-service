package com.script.ymy.controller;

import com.script.ymy.dto.ScriptTask;
import com.script.ymy.service.ScriptTaskService;
import com.script.ymy.vo.req.AllthemReq;
import com.script.ymy.vo.req.SaveTaskReq;
import com.script.ymy.vo.req.SelectTaskListReq;
import com.script.ymy.vo.resp.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Slf4j
@Controller
@RequestMapping("/task/")
public class ScriptTaskController {

    @Autowired
    private ScriptTaskService taskService;


    @ResponseBody
    @PostMapping("/web/findTaskList")
    public Result<List<ScriptTask>> findTaskList(@RequestBody SelectTaskListReq req){
        try {
            return Result.<List<ScriptTask> >builder().code(Result.SUCCESS_CODE).res(taskService.findTaskList(req)).build();
        } catch (Exception e) {
            return Result.<List<ScriptTask>>builder().code(Result.FAILED_CODE).error(e.getMessage()).build();
        }
    }


    @ResponseBody
    @PostMapping("/web/saveTask")
    public Result<Integer> saveTask(@RequestBody SaveTaskReq saveReq){
        try {
            return Result.<Integer>builder().code(Result.SUCCESS_CODE).res(taskService.saveTask(saveReq)).build();
        } catch (Exception e) {
            return Result.<Integer>builder().code(Result.FAILED_CODE).error(e.getMessage()).build();
        }
    }


    @ResponseBody
    @PostMapping("/web/updateTask")
    public Result<Integer> updateTask(@RequestBody ScriptTask req){
        try {
            return Result.<Integer >builder().code(Result.SUCCESS_CODE).res(taskService.updateScriptTask(req)).build();
        } catch (Exception e) {
            return Result.<Integer>builder().code(Result.FAILED_CODE).error(e.getMessage()).build();
        }
    }

    @ResponseBody
    @PostMapping("/web/delTask/{taskId}")
    public Result<Integer> delTask(@PathVariable("taskId") String taskId){
        try {
            return Result.<Integer>builder().code(taskService.delTask(Integer.parseInt(taskId))).build();
        } catch (Exception e) {
            return Result.<Integer>builder().code(Result.FAILED_CODE).error(e.getMessage()).build();
        }
    }



    @ResponseBody
    @PostMapping("/selectRecentTask")
    public Result<ScriptTask> findAllThem(@RequestBody AllthemReq req){
        try {
            return Result.<ScriptTask >builder().code(Result.SUCCESS_CODE).res(taskService.selectOneTask(req)).build();
        } catch (Exception e) {
            return Result.<ScriptTask>builder().code(Result.FAILED_CODE).error(e.getMessage()).build();
        }
    }




    @ResponseBody
    @PostMapping("/import/file")
    public Result<Boolean> importThemTxt(@RequestParam("file") MultipartFile file,
                                         @RequestParam("userId") String  userId){
        try {
            boolean result=taskService.importThemFile(file,userId);
            if(result){
                return Result.<Boolean>builder().code(Result.SUCCESS_CODE).build();
            }else {
                return Result.<Boolean>builder().code(Result.FAILED_CODE).build();
            }
        } catch (Exception e) {
            return Result.<Boolean>builder().code(Result.FAILED_CODE).error(e.getMessage()).build();
        }
    }


    @ResponseBody
    @PostMapping("/auto/import/file")
    public Result<Boolean> importThaskAllUser(@RequestParam("file") MultipartFile file){
        try {
            boolean result=taskService.importTaskAllUser(file);
            if(result){
                return Result.<Boolean>builder().code(Result.SUCCESS_CODE).build();
            }else {
                return Result.<Boolean>builder().code(Result.FAILED_CODE).build();
            }
        } catch (Exception e) {
            return Result.<Boolean>builder().code(Result.FAILED_CODE).error(e.getMessage()).build();
        }
    }

}
