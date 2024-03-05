package com.script.ymy.service;

import com.script.ymy.dto.YmyTopicKey;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface YmyTopickeyService {

    int addTopicKey(YmyTopicKey topicKey);

    int updateTopicKey(YmyTopicKey topicKey);

    int delTopicKey(Integer ymyTopicKeyId);

    YmyTopicKey findTopicOne();

    List<YmyTopicKey> findTopicList(YmyTopicKey  topicKey);


}
