package com.script.ymy.controller;

import com.script.ymy.dto.YmyTopicDetail;
import com.script.ymy.service.YmyTopicDetailService;
import com.script.ymy.vo.resp.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@Controller
@RequestMapping("/topic/detail/")
public class YmyTopicDetailController {


    @Autowired
     private  YmyTopicDetailService detailService;

    /** 列表 **/
    @CrossOrigin
    @ResponseBody
    @PostMapping("/web/findTopicDetailList")
    public Result<List<YmyTopicDetail> > findTopicDetailList(@RequestBody YmyTopicDetail  ymyTopicDetail){
        try {
            return Result.<List<YmyTopicDetail>  >builder().code(Result.SUCCESS_CODE).res(detailService.findTopicDetailList(ymyTopicDetail)).build();
        } catch (Exception e) {
            return Result.<List<YmyTopicDetail> >builder().code(Result.FAILED_CODE).error(e.getMessage()).build();
        }
    }


    /** 更新 **/
    @CrossOrigin
    @ResponseBody
    @PostMapping("/web/updateTopicDetail")
    public Result<Integer> updateTopicDetail(@RequestBody YmyTopicDetail topicDetail){
        try {
            return Result.<Integer>builder().code(Result.SUCCESS_CODE).res(detailService.updateTopicDetail(topicDetail)).build();
        } catch (Exception e) {
            return Result.<Integer>builder().code(Result.FAILED_CODE).error(e.getMessage()).build();
        }
    }


    /** 删除 **/
    @CrossOrigin
    @ResponseBody
    @PostMapping("/web/del")
    public Result<Integer> delTopicDetail(@RequestBody YmyTopicDetail topicDetail){
        try {
            return Result.<Integer>builder().code(Result.SUCCESS_CODE).res(detailService.delTopicDetail(topicDetail)).build();
        } catch (Exception e) {
            return Result.<Integer>builder().code(Result.FAILED_CODE).error(e.getMessage()).build();
        }
    }


    /** 转化成 them **/
    @CrossOrigin
    @ResponseBody
    @PostMapping("/web/castToThem")
    public Result<Integer> castToThem(@RequestBody List<YmyTopicDetail> topicDetails ){
        try {
            return Result.<Integer >builder().code(Result.SUCCESS_CODE).res(detailService.topicCastThem(topicDetails)).build();
        } catch (Exception e) {
            return Result.<Integer>builder().code(Result.FAILED_CODE).error(e.getMessage()).build();
        }
    }


    /** 列表 **/
    @CrossOrigin
    @ResponseBody
    @PostMapping("/script/addTopicDetail")
    public Result<Integer> addTopicDetail(@RequestBody List<YmyTopicDetail> topicDetail){
        try {
            return Result.<Integer >builder().code(Result.SUCCESS_CODE).res(detailService.addTopicDetail(topicDetail)).build();
        } catch (Exception e) {
            return Result.<Integer>builder().code(Result.FAILED_CODE).error(e.getMessage()).build();
        }
    }


}
