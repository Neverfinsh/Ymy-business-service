package com.script.ymy.service.impl;

import com.script.ymy.dto.ThemDto;
import com.script.ymy.dto.YmyTopicDetail;
import com.script.ymy.mapper.ThemDtoMapper;
import com.script.ymy.mapper.YmyTopicDetailMapper;
import com.script.ymy.service.YmyTopicDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Service
public class YmyTopicDetailServiceImpl implements YmyTopicDetailService {

    @Autowired
    private YmyTopicDetailMapper topicDetailMapper;

    @Autowired
    private ThemDtoMapper themMapper;

    @Override
    public int addTopicDetail(List<YmyTopicDetail> topicDetail) {
        return topicDetailMapper.insertBathTopicDetail(topicDetail);
    }

    @Override
    public int updateTopicDetail(YmyTopicDetail ymyTopicDetail) {
        return topicDetailMapper.updateByPrimaryKey(ymyTopicDetail);
    }

    @Override
    public int delTopicDetail(YmyTopicDetail ymyTopicDetail) {
        return topicDetailMapper.deleteByPrimaryKey(ymyTopicDetail.getId());
    }

    @Override
    public int topicCastThem(List<YmyTopicDetail> topicDetails) {
        List<ThemDto> themList= new ArrayList<>();
        for (YmyTopicDetail  topicDetail: topicDetails) {
            ThemDto them=new ThemDto();
            them.setArticleThem(topicDetail.getTopicDetailComplete());
            them.setDeviceId(topicDetail.getDeviceId());
            them.setArticleNum(1);
            them.setCreateTime( new Date());
            them.setUpdateTime( new Date());
            them.setStatus(0);
            them.setUid(topicDetail.getUserId());
            them.setArticleSendTime(topicDetail.getThemSendTime());
            themList.add(them);
        }
        return  themMapper.insertBatch(themList);
    }

    @Override
    public List<YmyTopicDetail> findTopicDetailList(YmyTopicDetail topicDetail) {
        return topicDetailMapper.findTopicDetailList(topicDetail);
    }
}
