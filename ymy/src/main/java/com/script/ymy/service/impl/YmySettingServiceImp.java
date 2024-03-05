package com.script.ymy.service.impl;

import com.script.ymy.dto.YmySetting;
import com.script.ymy.mapper.YmySettingMapper;
import com.script.ymy.service.YmySettingService;
import com.script.ymy.vo.req.YmySettingReq;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class YmySettingServiceImp implements YmySettingService {

    @Autowired
    private YmySettingMapper settingMapper;

    @Override
    public int addSettting(YmySetting setting) {
        return settingMapper.insert(setting);
    }

    @Override
    public int updateSetting(YmySetting setting) {
        return settingMapper.updateByPrimaryKey(setting);
    }

    @Override
    public int delSetting(Integer settingId) {
        return settingMapper.deleteByPrimaryKey(settingId);
    }

    @Override
    public List<YmySetting> finSettingList(YmySettingReq ymySetting) {
        return settingMapper.findSettingList(ymySetting);
    }
}
