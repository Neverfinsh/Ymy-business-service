package com.script.ymy.mapper;

import com.script.ymy.dto.YmyTopicDetail;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface YmyTopicDetailMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(YmyTopicDetail record);

     int insertBathTopicDetail(List<YmyTopicDetail> list);

    int insertSelective(YmyTopicDetail record);

    YmyTopicDetail selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(YmyTopicDetail record);

    int updateByPrimaryKey(YmyTopicDetail record);

     List<YmyTopicDetail> findTopicDetailList(YmyTopicDetail ymyTopicDetail);
}