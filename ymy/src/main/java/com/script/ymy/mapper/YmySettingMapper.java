package com.script.ymy.mapper;

import com.script.ymy.dto.YmySetting;
import com.script.ymy.vo.req.YmySettingReq;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface YmySettingMapper {

    int deleteByPrimaryKey(Integer id);

    int insert(YmySetting record);

    int insertSelective(YmySetting record);

    YmySetting selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(YmySetting record);

    int updateByPrimaryKey(YmySetting record);

    List<YmySetting> findSettingList(YmySettingReq req);
}