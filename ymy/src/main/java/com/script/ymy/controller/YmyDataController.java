package com.script.ymy.controller;

import com.script.ymy.dto.YmyData;
import com.script.ymy.dto.YmyUser;
import com.script.ymy.service.YmyDataService;
import com.script.ymy.service.YmyUserService;
import com.script.ymy.vo.req.SelectDataListReq;
import com.script.ymy.vo.req.WebLoginReq;
import com.script.ymy.vo.resp.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@Controller
@RequestMapping("/data/")
public class YmyDataController {

    @Autowired
    private YmyDataService ymyDataService;

    @CrossOrigin
    @ResponseBody
    @PostMapping("/web/recordList")
    public Result<List<YmyData>> recordList(@RequestBody SelectDataListReq  listReq){
        try {
            return Result.<List<YmyData> >builder().code(Result.SUCCESS_CODE).res(ymyDataService.listRecord(listReq)).build();
        } catch (Exception e) {
            return Result.<List<YmyData>>builder().code(Result.FAILED_CODE).error(e.getMessage()).build();
        }
    }


    @CrossOrigin
    @ResponseBody
    @PostMapping("/web/recordDetail")
    public Result<Integer> recordDetail(@RequestBody YmyUser YmyUser){
        try {
            return Result.<Integer >builder().code(Result.SUCCESS_CODE).res(null).build();
        } catch (Exception e) {
            return Result.<Integer>builder().code(Result.FAILED_CODE).error(e.getMessage()).build();
        }
    }



    @CrossOrigin
    @ResponseBody
    @PostMapping("/web/logout")
    public Result<Integer> logout(){
        try {
            return Result.<Integer >builder().code(Result.SUCCESS_CODE).res(null).build();
        } catch (Exception e) {
            return Result.<Integer>builder().code(Result.FAILED_CODE).error(e.getMessage()).build();
        }
    }







}
