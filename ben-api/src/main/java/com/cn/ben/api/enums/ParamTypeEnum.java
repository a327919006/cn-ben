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

    public static String format(Byte value) {
        switch (value) {
            case 0:
                return "form";
            case 1:
                return "body";
            default:
                return "";
        }
    }

    public static ParamTypeEnum parse(byte value) {
        switch (value) {
            case 0:
                return FORM;
            case 1:
                return BODY;
            default:
                return FORM;
        }
    }
}
