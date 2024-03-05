package com.script.ymy.controller;

import com.script.ymy.dto.YmyTopicKey;
import com.script.ymy.dto.YmyUser;
import com.script.ymy.service.YmyTopicDetailService;
import com.script.ymy.service.YmyTopickeyService;
import com.script.ymy.service.YmyUserService;
import com.script.ymy.vo.req.WebLoginReq;
import com.script.ymy.vo.resp.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@Controller
@RequestMapping("/topicKey/")
public class YmyTopicKeyController {

    @Autowired
    private YmyTopickeyService  topickeyService;

    @CrossOrigin
    @ResponseBody
    @PostMapping("/web/addTopic")
    public Result<Integer> addTopic(@RequestBody YmyTopicKey topicKey){
        try {
            return Result.<Integer >builder().code(Result.SUCCESS_CODE).res(topickeyService.addTopicKey(topicKey)).build();
        } catch (Exception e) {
            return Result.<Integer>builder().code(Result.FAILED_CODE).error(e.getMessage()).build();
        }
    }

    @CrossOrigin
    @ResponseBody
    @PostMapping("/web/updateTopic")
    public Result<Integer> updateTopic(@RequestBody YmyTopicKey topicKey){
        try {
            return Result.<Integer >builder().code(Result.SUCCESS_CODE).res(topickeyService.updateTopicKey(topicKey)).build();
        } catch (Exception e) {
            return Result.<Integer>builder().code(Result.FAILED_CODE).error(e.getMessage()).build();
        }
    }

    @CrossOrigin
    @ResponseBody
    @PostMapping("/web/findTopicList")
    public Result<List<YmyTopicKey>> findTopicList(@RequestBody YmyTopicKey  topicKey){
        try {
            return Result.<List<YmyTopicKey>>builder().code(Result.SUCCESS_CODE).res(topickeyService.findTopicList(topicKey)).build();
        } catch (Exception e) {
            return Result.<List<YmyTopicKey>>builder().code(Result.FAILED_CODE).error(e.getMessage()).build();
        }
    }


    @CrossOrigin
    @ResponseBody
    @PostMapping("/web/delTopic/{ymyTopicKeyId}")
    public Result<Integer> delTopic(@PathVariable("ymyTopicKeyId")  String  ymyTopicKeyId){
        try {
            return Result.<Integer>builder().code(Result.SUCCESS_CODE).res(topickeyService.delTopicKey(Integer.parseInt(ymyTopicKeyId))).build();
        } catch (Exception e) {
            return Result.<Integer>builder().code(Result.FAILED_CODE).error(e.getMessage()).build();
        }
    }

    @CrossOrigin
    @ResponseBody
    @GetMapping("/script/findTopicOne")
    public Result<YmyTopicKey> findTopicOne(){
        try {
            return Result.<YmyTopicKey>builder().code(Result.SUCCESS_CODE).res(topickeyService.findTopicOne()).build();
        } catch (Exception e) {
            return Result.<YmyTopicKey>builder().code(Result.FAILED_CODE).error(e.getMessage()).build();
        }
    }



}
