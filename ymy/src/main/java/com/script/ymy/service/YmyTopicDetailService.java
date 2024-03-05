package com.script.ymy.service;

import com.script.ymy.dto.YmyTopicDetail;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface YmyTopicDetailService {

   int addTopicDetail(List<YmyTopicDetail> topicDetail);

   int updateTopicDetail(YmyTopicDetail ymyTopicDetail);

   int delTopicDetail(YmyTopicDetail ymyTopicDetail);

   int topicCastThem(List<YmyTopicDetail> topicDetails);

   List<YmyTopicDetail> findTopicDetailList(YmyTopicDetail topicDetail);

}
