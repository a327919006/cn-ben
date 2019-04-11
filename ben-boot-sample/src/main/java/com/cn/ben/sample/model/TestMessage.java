package com.cn.ben.sample.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

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
public class TestMessage implements Serializable {
    private String title;
    private String content;
}
