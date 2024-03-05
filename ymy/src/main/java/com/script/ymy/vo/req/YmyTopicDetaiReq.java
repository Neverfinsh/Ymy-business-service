package com.script.ymy.vo.req;

import java.io.Serializable;

public class YmyTopicDetaiReq implements Serializable {
    private Integer id;

    private String userId;

    private String deviceId;

    private Integer topicKeyId;

    private String topicDetail;

    private String topicDetailComplete;

    private Integer status;

    private String createTime;

    private String remark;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public Integer getTopicKeyId() {
        return topicKeyId;
    }

    public void setTopicKeyId(Integer topicKeyId) {
        this.topicKeyId = topicKeyId;
    }

    public String getTopicDetail() {
        return topicDetail;
    }

    public void setTopicDetail(String topicDetail) {
        this.topicDetail = topicDetail;
    }

    public String getTopicDetailComplete() {
        return topicDetailComplete;
    }

    public void setTopicDetailComplete(String topicDetailComplete) {
        this.topicDetailComplete = topicDetailComplete;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}