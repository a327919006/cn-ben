package com.cn.ben.api.enums;

/**
 * 通知状态
 *
 * @author Chen Nan
 */
public enum NotifyStatusEnum {
    /**
     * 0未通知
     */
    WAIT((byte) 0),
    /**
     * 1通知成功
     */
    SUCCESS((byte) 1),
    /**
     * 2已通知(业务方处理失败)
     */
    BUSINESS_FAIL((byte) 2),
    /**
     * 3Http请求异常(业务方down机，网络波动等情况)
     */
    REQUEST_FAIL((byte) 3),
    /**
     * 4通知失败（超过通知次数）
     */
    FAIL((byte) 4);

    private byte value;

    NotifyStatusEnum(byte value) {
        this.value = value;
    }

    public byte getValue() {
        return value;
    }
}
