package com.script.ymy.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class YmySetting implements Serializable {

    private Integer id;

    private String module;

    private String code;

    private String name;

    private String remark;

    private  String deviceId;

    private  String accountId;

    private static final long serialVersionUID = 1L;

}