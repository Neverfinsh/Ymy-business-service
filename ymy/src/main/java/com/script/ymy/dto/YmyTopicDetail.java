package com.script.ymy.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class YmyTopicDetail implements Serializable {
    private Integer id;

    private String userId;

    private String deviceId;

    private Integer topicKeyId;

    private String topicDetail;

    private String topicDetailComplete;

    private Integer status;

    private String createTime;

    private String remark;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date themSendTime;


}