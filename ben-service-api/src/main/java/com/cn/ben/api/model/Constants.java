package com.cn.ben.api.model;

/**
 * <p>Title: Constants</p>
 * <p>Description: 常量类</p>
 *
 * @author Chen Nan
 */
public class Constants {

    private Constants() {
        throw new RuntimeException("Constants.class can't be instantiated");
    }

    /* 通用应答码 URC-Universal Response Code */
    /**
     * 应答码：成功
     */
    public static final int CODE_SUCCESS = 0;
    /**
     * 应答码：失败
     */
    public static final int CODE_FAILURE = 1;

    /**
     * 用户session
     */
    public static final String SESSION_USER = "session_user";

    /**
     * MSG
     */
    public static final String MSG_SUCCESS = "SUCCESS";

    /**
     * Queue
     */
    public static final String QUEUE_NOTIFY = "notify.queue";

    /**
     * key
     */
    public static final String KEY_PAGE_NUM = "pageNum";
    public static final String KEY_PAGE_SIZE = "pageSize";
    public static final String KEY_COUNT = "count";
    public static final String KEY_ORDER_BY = "orderBy";
}
