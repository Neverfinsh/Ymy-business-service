package com.script.ymy.vo.req;
import lombok.Data;

@Data
public class SelectDataListReq {
    private  String  uid;
    private  String  channel;
    private  String  deviceId;
    private  Integer  status;
    private  String   startTime ;
    private  String   endTime;
}
