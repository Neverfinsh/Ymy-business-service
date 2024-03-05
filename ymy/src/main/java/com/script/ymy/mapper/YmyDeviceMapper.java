package com.script.ymy.mapper;

import com.script.ymy.dto.YmyDevice;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface YmyDeviceMapper {

    int deleteByPrimaryKey(Integer id);

    int insert(YmyDevice record);

    int insertSelective(YmyDevice record);

    YmyDevice selectByPrimaryKey(Integer id);

    List<YmyDevice> selectByUserAccount(String  deviceUserAccount);

    int updateByPrimaryKeySelective(YmyDevice record);

    int updateByPrimaryKey(YmyDevice record);
}