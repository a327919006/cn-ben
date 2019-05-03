package com.cn.ben.api.enums;

/**
 * 通知状态
 *
 * @author Chen Nan
 */
public enum NotifyStatusEnum {
    /**
     * 0 未通知
     */
    WAIT((byte) 0),
    /**
     * 1 通知成功
     */
    SUCCESS((byte) 1),
    /**
     * 2 已通知(业务方处理失败)
     */
    BUSINESS_FAIL((byte) 2),
    /**
     * 3 Http请求异常(业务方down机，网络波动等情况)
     */
    REQUEST_FAIL((byte) 3),
    /**
     * 4 通知失败（超过通知次数）
     */
    FAIL((byte) 4);

    private byte value;

    NotifyStatusEnum(byte value) {
        this.value = value;
    }

    public byte getValue() {
        return value;
    }

    public static String format(Byte value) {
        switch (value) {
            case 0:
                return "未通知";
            case 1:
                return "通知成功";
            case 2:
                return "业务方处理失败";
            case 3:
                return "Http请求异常";
            case 4:
                return "通知失败";
            default:
                return "";
        }
    }
}
