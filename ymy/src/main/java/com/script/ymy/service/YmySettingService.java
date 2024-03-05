package com.script.ymy.service;

import com.script.ymy.dto.YmySetting;
import com.script.ymy.vo.req.YmySettingReq;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface YmySettingService {

    int  addSettting (YmySetting setting);

     int  updateSetting (YmySetting setting);

     int  delSetting (Integer settingId);

     List<YmySetting> finSettingList(YmySettingReq ymySetting);

}
