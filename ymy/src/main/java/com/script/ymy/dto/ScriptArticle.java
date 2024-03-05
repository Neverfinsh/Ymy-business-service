package com.script.ymy.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Data
public class ScriptArticle implements Serializable {
    private Integer id;

    private String uid;

    private String deviceId;

    private Integer articleNum;

    private String articleThem;

    private String articleTitle;

    private String articleContent;

    private List<String> imgList;

    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
    private Date articleSendTime;

    private Integer status;

    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
    private Date createTime;

    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
    private Date updateTime;


}