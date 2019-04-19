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

    public static MethodEnum parse(byte value){
        switch (value) {
            case 0:
                return GET;
            case 1:
                return POST;
            case 2:
                return HEAD;
            case 3:
                return OPTIONS;
            case 4:
                return PUT;
            case 5:
                return DELETE;
            case 6:
                return TRACE;
            case 7:
                return PATCH;
            default:
                return POST;
        }
    }
}
