package com.cn.ben.api.model.po;

import java.io.Serializable;
import java.time.LocalDateTime;

public class NotifyRecord implements Serializable {
    private String id;

    private String notifyUrl;

    private String notifyHeader;

    private Short notifyTimeout;

    private Short notifyTimes;

    private Byte notifyStatus;

    private String businessName;

    private String businessId;

    private LocalDateTime createTime;

    private LocalDateTime updateTime;

    private String notifyContent;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNotifyUrl() {
        return notifyUrl;
    }

    public void setNotifyUrl(String notifyUrl) {
        this.notifyUrl = notifyUrl;
    }

    public String getNotifyHeader() {
        return notifyHeader;
    }

    public void setNotifyHeader(String notifyHeader) {
        this.notifyHeader = notifyHeader;
    }

    public Short getNotifyTimeout() {
        return notifyTimeout;
    }

    public void setNotifyTimeout(Short notifyTimeout) {
        this.notifyTimeout = notifyTimeout;
    }

    public Short getNotifyTimes() {
        return notifyTimes;
    }

    public void setNotifyTimes(Short notifyTimes) {
        this.notifyTimes = notifyTimes;
    }

    public Byte getNotifyStatus() {
        return notifyStatus;
    }

    public void setNotifyStatus(Byte notifyStatus) {
        this.notifyStatus = notifyStatus;
    }

    public String getBusinessName() {
        return businessName;
    }

    public void setBusinessName(String businessName) {
        this.businessName = businessName;
    }

    public String getBusinessId() {
        return businessId;
    }

    public void setBusinessId(String businessId) {
        this.businessId = businessId;
    }

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }

    public LocalDateTime getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(LocalDateTime updateTime) {
        this.updateTime = updateTime;
    }

    public String getNotifyContent() {
        return notifyContent;
    }

    public void setNotifyContent(String notifyContent) {
        this.notifyContent = notifyContent;
    }
}