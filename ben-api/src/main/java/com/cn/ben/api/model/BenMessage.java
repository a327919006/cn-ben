package com.cn.ben.api.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * <p>Title:</p>
 * <p>Description:</p>
 *
 * @author Chen Nan
 * @date 2019/4/11.
 */
@Getter
@Setter
@ToString
public class BenMessage {
    private String type;
    private String content;
}
