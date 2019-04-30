package com.cn.ben.service;

import com.cn.ben.service.listener.ApplicationReadyListener;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * 服务层启动类
 *
 * @author Chen Nan
 */
@SpringBootApplication
@MapperScan("com.cn.ben.dal.mapper")
@ComponentScan(basePackages = {
        "com.cn.ben.dal.mapper",
        "com.cn.ben.service.config",
        "com.cn.ben.service.mq"})
public class ServiceApplication {
    public static void main(String[] args) {
        SpringApplication application = new SpringApplication(ServiceApplication.class);
        application.addListeners(new ApplicationReadyListener(args));
        application.run(args);
    }
}
