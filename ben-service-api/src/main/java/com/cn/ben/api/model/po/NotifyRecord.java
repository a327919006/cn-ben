package com.cn.ben.api.model.po;

import java.io.Serializable;
import java.time.LocalDateTime;

public class NotifyRecord implements Serializable {
    private String id;

    private String notifyUrl;

    private Byte notifyMethod;

    private String notifyHeader;

    private Byte notifyParamType;

    private Short notifyTimeout;

    private String successFlag;

    private Short notifyTimes;

    private Byte notifyStatus;

    private String businessName;

    private String businessId;

    private LocalDateTime createTime;

    private LocalDateTime updateTime;

    private String notifyParam;

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

    public Byte getNotifyMethod() {
        return notifyMethod;
    }

    public void setNotifyMethod(Byte notifyMethod) {
        this.notifyMethod = notifyMethod;
    }

    public String getNotifyHeader() {
        return notifyHeader;
    }

    public void setNotifyHeader(String notifyHeader) {
        this.notifyHeader = notifyHeader;
    }

    public Byte getNotifyParamType() {
        return notifyParamType;
    }

    public void setNotifyParamType(Byte notifyParamType) {
        this.notifyParamType = notifyParamType;
    }

    public Short getNotifyTimeout() {
        return notifyTimeout;
    }

    public void setNotifyTimeout(Short notifyTimeout) {
        this.notifyTimeout = notifyTimeout;
    }

    public String getSuccessFlag() {
        return successFlag;
    }

    public void setSuccessFlag(String successFlag) {
        this.successFlag = successFlag;
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

    public String getNotifyParam() {
        return notifyParam;
    }

    public void setNotifyParam(String notifyParam) {
        this.notifyParam = notifyParam;
    }
}