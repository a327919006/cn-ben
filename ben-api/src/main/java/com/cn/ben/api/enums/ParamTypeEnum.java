package com.cn.ben.api.enums;

/**
 * Http参数类型枚举
 *
 * @author Chen Nan
 */
public enum ParamTypeEnum {
    /**
     * 表单
     */
    FORM((byte) 0),
    BODY((byte) 1);

    private byte value;

    ParamTypeEnum(byte value) {
        this.value = value;
    }

    public byte getValue() {
        return value;
    }
}
