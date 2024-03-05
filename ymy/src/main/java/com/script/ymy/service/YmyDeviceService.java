package com.script.ymy.service;

import com.script.ymy.dto.YmyDevice;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface YmyDeviceService {

    List<YmyDevice> findDevicesByUser(String account);

    int addDevice(YmyDevice ymyDevice);

    int delDevice(String  deviceId);

    int updateDevice(YmyDevice  ymyDevice);

}
