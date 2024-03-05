package com.script.ymy.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.io.Serializable;

@Data
public class YmyData implements Serializable {
    private Integer id;
    private String  uid;
    private String  devicedId;
    private Integer themTotalCount;
    private Integer totalCount;
    private Integer finishedCount;
    private Integer unfinishedCount;
    private String channel;
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
    private String beginTime;

}