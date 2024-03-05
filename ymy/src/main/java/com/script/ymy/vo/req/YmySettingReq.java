package com.script.ymy.vo.req;

import lombok.Data;

import java.io.Serializable;

@Data
public class YmySettingReq implements Serializable {

    private String module;

    private  String deviceId;

    private  String accountId;


}