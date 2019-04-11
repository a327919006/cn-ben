package com.cn.ben.sample.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * <p>Title:</p>
 * <p>Description:
 * swagger配置
 * </p>
 *
 * @author Chen Nan
 * @date 2019/3/31.
 */
@Configuration
@EnableWebMvc
@EnableSwagger2
@ComponentScan(basePackages = {"com.cn.ben.sample.controller"})  //需要扫描的包路径
public class SwaggerConfig extends WebMvcConfigurationSupport {


    @Bean
    public Docket swaggerSpringMvcPlugin() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .groupName("ben-sample")
                .select()   // 选择那些路径和api会生成document
                .apis(RequestHandlerSelectors.basePackage("com.cn.ben.sample.controller"))
                .paths(PathSelectors.any())
                .build()
        ;
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("ben-sample")
                .termsOfServiceUrl("服务条款地址")
                .description("ben-sample")
                .license("License Version 1.0")
                .licenseUrl("个人网址")
                .version("1.0").build();
    }

}
