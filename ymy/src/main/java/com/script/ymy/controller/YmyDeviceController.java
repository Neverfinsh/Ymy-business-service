package com.script.ymy.controller;

import com.script.ymy.dto.YmyDevice;
import com.script.ymy.service.YmyDeviceService;
import com.script.ymy.vo.resp.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@Controller
@RequestMapping("/device/")
public class YmyDeviceController {

    @Autowired
    private YmyDeviceService ymyDeviceService;

    @CrossOrigin
    @ResponseBody
    @GetMapping("/web/findDeviceByUser/{userAccount}")
    public Result<List<YmyDevice>> findDeviceListByUser(@PathVariable("userAccount") String userAccount){
        try {
            return Result.<List<YmyDevice>>builder().code(Result.SUCCESS_CODE).res(ymyDeviceService.findDevicesByUser(userAccount)).build();
        } catch (Exception e) {
            return Result.<List<YmyDevice>>builder().code(Result.FAILED_CODE).error(e.getMessage()).build();
        }
    }


    @CrossOrigin
    @ResponseBody
    @PostMapping("/web/addDevice")
    public Result<Integer> addDevice(@RequestBody YmyDevice device){
        try {
            return Result.<Integer >builder().code(Result.SUCCESS_CODE).res(ymyDeviceService.addDevice(device)).build();
        } catch (Exception e) {
            return Result.<Integer>builder().code(Result.FAILED_CODE).error(e.getMessage()).build();
        }
    }


    @CrossOrigin
    @ResponseBody
    @GetMapping("/web/delDevice/{deviceId}")
    public Result<Integer> delDevice(@PathVariable("deviceId") String  deviceId){
        try {
            return Result.<Integer >builder().code(Result.SUCCESS_CODE).res(ymyDeviceService.delDevice(deviceId)).build();
        } catch (Exception e) {
            return Result.<Integer>builder().code(Result.FAILED_CODE).error(e.getMessage()).build();
        }
    }


    @CrossOrigin
    @ResponseBody
    @PostMapping("/web/updateDevice")
    public Result<Integer> updateDevice(@RequestBody YmyDevice  device){
        try {
            return Result.<Integer >builder().code(Result.SUCCESS_CODE).res(ymyDeviceService.updateDevice(device)).build();
        } catch (Exception e) {
            return Result.<Integer>builder().code(Result.FAILED_CODE).error(e.getMessage()).build();
        }
    }




}
