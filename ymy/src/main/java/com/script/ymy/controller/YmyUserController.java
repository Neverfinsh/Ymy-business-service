package com.script.ymy.controller;

import com.script.ymy.dto.YmyUser;
import com.script.ymy.service.YmyUserService;
import com.script.ymy.vo.req.WebLoginReq;
import com.script.ymy.vo.resp.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Slf4j
@Controller
@RequestMapping("/user/")
public class YmyUserController {

    @Autowired
    private YmyUserService  ymyUserService;

    @CrossOrigin
    @ResponseBody
    @PostMapping("/web/login")
    public Result<YmyUser> login(@RequestBody WebLoginReq loginReq){
        try {
            return Result.<YmyUser >builder().code(Result.SUCCESS_CODE).res(ymyUserService.login(loginReq)).build();
        } catch (Exception e) {
            return Result.<YmyUser>builder().code(Result.FAILED_CODE).error(e.getMessage()).build();
        }
    }

    @CrossOrigin
    @ResponseBody
    @PostMapping("/web/regUser")
    public Result<Integer> regUser(@RequestBody YmyUser YmyUser){
        try {
            return Result.<Integer >builder().code(Result.SUCCESS_CODE).res(ymyUserService.addUer(YmyUser)).build();
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
