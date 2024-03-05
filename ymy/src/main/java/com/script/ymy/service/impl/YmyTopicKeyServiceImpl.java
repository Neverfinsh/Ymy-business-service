package com.script.ymy.service.impl;

import com.script.ymy.dto.YmyTopicKey;
import com.script.ymy.mapper.YmyTopicKeyMapper;
import com.script.ymy.service.YmyTopickeyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class YmyTopicKeyServiceImpl implements YmyTopickeyService {

    @Autowired
    private YmyTopicKeyMapper topicKeyMapper;

    @Override
    public int addTopicKey(YmyTopicKey topicKey) {
           return  topicKeyMapper.insert(topicKey);
    }

    @Override
    public int updateTopicKey(YmyTopicKey topicKey) {
        return topicKeyMapper.updateByPrimaryKey(topicKey);
    }

    @Override
    public int delTopicKey(Integer ymyTopicKeyId) {
        return  topicKeyMapper.deleteByPrimaryKey(ymyTopicKeyId);
    }

    @Override
    public YmyTopicKey findTopicOne() {
        return topicKeyMapper.selectOneByUid();
    }

    @Override
    public List<YmyTopicKey> findTopicList(YmyTopicKey  topicKey) {
        return topicKeyMapper.selectListByUid(topicKey);
    }

}
