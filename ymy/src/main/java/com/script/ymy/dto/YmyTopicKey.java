package com.script.ymy.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class YmyTopicKey implements Serializable {

    private Integer id;

    private String name;

    private Integer status;

    private String userId;

    private String deviceId;

    private Integer type;

    private String remark;

    private String createTime;

    private String sendTime;

    private static final long serialVersionUID = 1L;

}