package com.script.ymy.mapper;

import com.script.ymy.dto.YmyTopicKey;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface YmyTopicKeyMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(YmyTopicKey record);

    int insertSelective(YmyTopicKey record);

    YmyTopicKey selectByPrimaryKey(Integer id);

    YmyTopicKey selectOneByUid();

    List<YmyTopicKey> selectListByUid(YmyTopicKey   topicKey);

    int updateByPrimaryKeySelective(YmyTopicKey record);

    int updateByPrimaryKey(YmyTopicKey record);
}