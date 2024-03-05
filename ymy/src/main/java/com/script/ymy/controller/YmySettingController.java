package com.script.ymy.controller;

import com.script.ymy.dto.YmySetting;
import com.script.ymy.dto.YmyTopicKey;
import com.script.ymy.service.YmySettingService;
import com.script.ymy.vo.req.YmySettingReq;
import com.script.ymy.vo.resp.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@Controller
@RequestMapping("/setting/")
public class YmySettingController {

    @Autowired
    private YmySettingService settingService;

    /** 新增配置**/
    @CrossOrigin
    @ResponseBody
    @PostMapping("/web/addSetting")
    public Result<Integer> addSettting(@RequestBody YmySetting setting){
        try {
            return Result.<Integer >builder().code(Result.SUCCESS_CODE).res(settingService.addSettting(setting)).build();
        } catch (Exception e) {
            return Result.<Integer>builder().code(Result.FAILED_CODE).error(e.getMessage()).build();
        }
    }

    /** 编辑配置**/
    @CrossOrigin
    @ResponseBody
    @PostMapping("/web/updateSetting")
    public Result<Integer> updateSettting(@RequestBody YmySetting setting){
        try {
            return Result.<Integer >builder().code(Result.SUCCESS_CODE).res(settingService.updateSetting(setting)).build();
        } catch (Exception e) {
            return Result.<Integer>builder().code(Result.FAILED_CODE).error(e.getMessage()).build();
        }
    }

    /** 编辑配置**/
    @CrossOrigin
    @ResponseBody
    @PostMapping("/web/delSetting/{settingId}")
    public Result<Integer> delSetting(@PathVariable("settingId") String  settingId){
        try {
            return Result.<Integer >builder().code(Result.SUCCESS_CODE).res(settingService.delSetting(Integer.parseInt(settingId))).build();
        } catch (Exception e) {
            return Result.<Integer>builder().code(Result.FAILED_CODE).error(e.getMessage()).build();
        }
    }


    /** 查询配置列表**/
    @CrossOrigin
    @ResponseBody
    @PostMapping("/web/findSettingList")
    public Result<List<YmySetting>> findTopicList(@RequestBody YmySettingReq settingReq){
        try {
            return Result.<List<YmySetting>>builder().code(Result.SUCCESS_CODE).res(settingService.finSettingList(settingReq)).build();
        } catch (Exception e) {
            return Result.<List<YmySetting>>builder().code(Result.FAILED_CODE).error(e.getMessage()).build();
        }
    }

}
