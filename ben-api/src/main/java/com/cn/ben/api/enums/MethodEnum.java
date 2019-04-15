package com.cn.ben.api.enums;

/**
 * Http方法枚举
 *
 * @author Chen Nan
 */
public enum MethodEnum {
    /**
     * GET
     */
    GET((byte) 0),
    POST((byte) 1),
    HEAD((byte) 2),
    OPTIONS((byte) 3),
    PUT((byte) 4),
    DELETE((byte) 5),
    TRACE((byte) 6),
    PATCH((byte) 7);

    private byte value;

    MethodEnum(byte value) {
        this.value = value;
    }

    public byte getValue() {
        return value;
    }
}
