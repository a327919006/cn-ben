package com.cn.ben.api.model.dto.memory;

import lombok.Getter;
import lombok.Setter;

/**
 * 内存信息对象
 *
 * @author Chen Nan
 */
@Getter
@Setter
public class BenMemory {
    private Long total;
    private Long max;
    private Long free;
    private Long use;

    @Override
    public String toString() {
        String memoryInfo = "";
        memoryInfo += "JVM已用内存：" + this.use + " MB, ";
        memoryInfo += "JVM空闲内存：" + this.free + " MB, ";
        memoryInfo += "JVM总内存：" + this.total + " MB, ";
        memoryInfo += "JVM最大内存：" + this.max + " MB, ";

        return memoryInfo;
    }
}
