package com.script.ymy.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class ScriptTask implements Serializable {
    private static final long serialVersionUID = 1L;
    private Integer id;
    private String uid;
    private String deviceId;
    private Integer articleNum;
    private String articleThem;
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
    private Date articleSendTime;
    private Integer status;
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
    private Date createTime;
    private Date updateTime;
}