package com.script.ymy.vo.req;


import lombok.Data;

@Data
public class SelectDataThemReq {
    private  String   deviceId;
    private  String   userId;
    private  String   startTime;
    private  String   endTime;
}
