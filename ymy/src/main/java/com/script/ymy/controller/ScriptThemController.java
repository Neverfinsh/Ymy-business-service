package com.script.ymy.controller;

import com.script.ymy.dto.ThemDto;
import com.script.ymy.service.ScriptService;
import com.script.ymy.vo.req.AllthemReq;
import com.script.ymy.vo.req.ThemVo;
import com.script.ymy.vo.resp.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Slf4j
@Controller
@RequestMapping("/them")
public class ScriptThemController {

    @Autowired
    private ScriptService scriptService;

    /*****  页面交互接口  ********/


    @CrossOrigin
    @ResponseBody
    @PostMapping("/web/findThemList")
    public Result<List<ThemDto>> findThemList(@RequestBody AllthemReq req){
        try {
            return Result.<List<ThemDto> >builder().code(Result.SUCCESS_CODE).res(scriptService.findThemList(req)).build();
        } catch (Exception e) {
            return Result.<List<ThemDto>>builder().code(Result.FAILED_CODE).error(e.getMessage()).build();
        }
    }

    @CrossOrigin
    @ResponseBody
    @PostMapping("/web/updateThem")
    public Result<Integer> updateThem(@RequestBody ThemDto themDto){
        try {
            return Result.<Integer >builder().code(Result.SUCCESS_CODE).res(scriptService.updateScriptThem(themDto)).build();
        } catch (Exception e) {
            return Result.<Integer>builder().code(Result.FAILED_CODE).error(e.getMessage()).build();
        }
    }

    @CrossOrigin
    @ResponseBody
    @PostMapping("/web/delThem/{themId}")
    public Result<Integer> delThem(@PathVariable("themId") Integer  themId){
        try {
            return Result.<Integer >builder().code(Result.SUCCESS_CODE).res(scriptService.delThem(themId)).build();
        } catch (Exception e) {
            return Result.<Integer>builder().code(Result.FAILED_CODE).error(e.getMessage()).build();
        }
    }
    /*****  页面交互接口  ********/
    @CrossOrigin
    @ResponseBody
    @PostMapping ("/script/findAllThem")
    public Result<ThemDto> findAllWithScript(@RequestBody AllthemReq req){
        try {
             return Result.<ThemDto >builder().code(Result.SUCCESS_CODE).res(scriptService.findThemListNoParams(req)).build();
        } catch (Exception e) {
             return Result.<ThemDto >builder().code(Result.FAILED_CODE).error(e.getMessage()).build();
        }
    }

    @CrossOrigin
    @ResponseBody
    @GetMapping ("/script/findAllThemWithOut")
    public Result<ThemDto> findAllThemWithOut(){
        try {
            return Result.<ThemDto >builder().code(Result.SUCCESS_CODE).res(scriptService.findThemListNo()).build();
        } catch (Exception e) {
            return Result.<ThemDto >builder().code(Result.FAILED_CODE).error(e.getMessage()).build();
        }
    }

    @CrossOrigin
    @ResponseBody
    @PostMapping("/script/updateThemStatus/{themId}")
    public Result<Integer> updateThemStatusScript(@PathVariable("themId") String themId){
        try {
            return Result.<Integer>builder().code(scriptService.findThemById(themId)).build();
        } catch (Exception e) {
            return Result.<Integer>builder().code(Result.FAILED_CODE).error(e.getMessage()).build();
        }
    }


    @CrossOrigin
    @ResponseBody
    @PostMapping("/allThem")
    public Result<ThemDto> findAllThem(@RequestBody AllthemReq allthemReq){
        try {
            return Result.<ThemDto >builder().code(Result.SUCCESS_CODE).res(scriptService.selectAllThem(allthemReq)).build();
        } catch (Exception e) {
            return Result.<ThemDto>builder().code(Result.FAILED_CODE).error(e.getMessage()).build();
        }
    }

    @CrossOrigin
    @ResponseBody
    @PostMapping("/findRecentOne")
    public Result<ThemDto> findOneThem(@RequestBody AllthemReq allthemReq){
        try {
            return Result.<ThemDto >builder().code(Result.SUCCESS_CODE).res(scriptService.selectOneThem(allthemReq)).build();
        } catch (Exception e) {
            return Result.<ThemDto>builder().code(Result.FAILED_CODE).error(e.getMessage()).build();
        }
    }


    @CrossOrigin
    @ResponseBody
    @PostMapping("/updateThemStatus/{themId}")
    public Result<Integer> updateThemStatus(@PathVariable("themId") String themId){
        try {
            return Result.<Integer>builder().code(scriptService.findThemById(themId)).build();
        } catch (Exception e) {
            return Result.<Integer>builder().code(Result.FAILED_CODE).error(e.getMessage()).build();
        }
    }

    @CrossOrigin
    @ResponseBody
    @PostMapping("/addThem")
    public Result<Integer> addThem(@RequestBody ThemVo themVo){
        try {
            return Result.<Integer>builder().code(scriptService.addThem(themVo)).build();
        } catch (Exception e) {
            return Result.<Integer>builder().code(Result.FAILED_CODE).error(e.getMessage()).build();
        }
    }

    @CrossOrigin
    @ResponseBody
    @PostMapping("/addBathThem")
    public Result<Integer> addBathThem(@RequestBody List<ThemVo> themVos){
        try {
            return Result.<Integer>builder().code(scriptService.addThemBath(themVos)).build();
        } catch (Exception e) {
            return Result.<Integer>builder().code(Result.FAILED_CODE).error(e.getMessage()).build();
        }
    }

    @CrossOrigin
    @ResponseBody
    @PostMapping("/addThemRandom")
    public Result<Integer> addThemRandom(@RequestBody ThemVo themVo){
        try {
            return Result.<Integer>builder().code(scriptService.addThemRandom(themVo)).build();
        } catch (Exception e) {
            return Result.<Integer>builder().code(Result.FAILED_CODE).error(e.getMessage()).build();
        }
    }

    @CrossOrigin
    @ResponseBody
    @PostMapping("/import/file")
    public Result<Boolean> importThemTxtObj(@RequestParam("file") MultipartFile file,
                                            @RequestParam("userId") String  userId){
        try {
            Boolean result=scriptService.importThemFile(file,userId);
            if(result){
                return Result.<Boolean>builder().code(Result.SUCCESS_CODE).build();
            }else {
                return Result.<Boolean>builder().code(Result.FAILED_CODE).build();
            }
        } catch (Exception e) {
            return Result.<Boolean>builder().code(Result.FAILED_CODE).error(e.getMessage()).build();
        }
    }


    @CrossOrigin
    @ResponseBody
    @PostMapping("/import/params/file")
    public Result<Boolean> importThemTxt(@RequestParam("file") MultipartFile file,
                                         @RequestParam("uid") String  uid,
                                         @RequestParam("deviceId") String  deviceId,
                                         @RequestParam("articleSendTime") String  articleSendTime,
                                         @RequestParam("articleNum") String  articleNum,
                                         @RequestParam("channel") String  channel){
        try {
          //  Boolean result=scriptService.importThemFile(file,userId);
            Boolean result=scriptService.importThemFileWithParams(file,uid,deviceId,articleSendTime,articleNum,channel);
            if(result){
                return Result.<Boolean>builder().code(Result.SUCCESS_CODE).build();
            }else {
                return Result.<Boolean>builder().code(Result.FAILED_CODE).build();
            }
        } catch (Exception e) {
            return Result.<Boolean>builder().code(Result.FAILED_CODE).error(e.getMessage()).build();
        }
    }



    @CrossOrigin
    @ResponseBody
    @PostMapping("/import/all/file")
    public Result<Boolean> importThemTxtAllUser(@RequestParam("file") MultipartFile file){
        try {
            Boolean result=scriptService.importThemAllFile(file);
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
