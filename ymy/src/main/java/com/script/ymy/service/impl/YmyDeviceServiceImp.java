package com.script.ymy.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.script.ymy.dto.YmyDevice;
import com.script.ymy.mapper.YmyDeviceMapper;
import com.script.ymy.service.YmyDeviceService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Slf4j
@Service
public class YmyDeviceServiceImp implements YmyDeviceService {

    @Autowired
    private YmyDeviceMapper deviceMapper;

    @Override
    public List<YmyDevice> findDevicesByUser(String account) {
        log.info("=======根据用户查询设备列表,请求参数 account:{}", JSONObject.toJSONString(account));
        List<YmyDevice> list=   deviceMapper.selectByUserAccount(account);
        return list;
    }

    @Override
    public int addDevice(YmyDevice ymyDevice) {
        log.info("=======新增设备,请求参数 account:{}", JSONObject.toJSONString(ymyDevice));
        ymyDevice.setStatus("0");
        ymyDevice.setCreateTime(new Date());
        ymyDevice.setUpdateTime(new Date());
        int addRes= deviceMapper.insert(ymyDevice);
        return addRes;
    }

    @Override
    public int delDevice(String deviceId) {
        log.info("=======删除设备,请求参数 account:{}", JSONObject.toJSONString(deviceId));
        int delRes= deviceMapper.deleteByPrimaryKey(Integer.parseInt(deviceId));
        return delRes;
    }

    @Override
    public int updateDevice(YmyDevice ymyDevice) {
        log.info("=======更新设备,请求参数 account:{}", JSONObject.toJSONString(ymyDevice));
        int updateRes=  deviceMapper.updateByPrimaryKeySelective(ymyDevice);
        return updateRes;
    }
}
