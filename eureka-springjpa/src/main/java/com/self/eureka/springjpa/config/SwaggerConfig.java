package com.self.eureka.springjpa.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableSwagger2
@Configuration
public class SwaggerConfig {

    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2).apiInfo(apiInfo())
                // 是否开启
                .enable(true)
                .select()
                // 扫描的路径包
                .apis(RequestHandlerSelectors.basePackage("com.self.eureka.springjpa.controller"))
                // 指定路径处理PathSelectors.any()代表所有的路径
                .paths(PathSelectors.any()).build().pathMapping("/");
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("RBAC")
                .description("完整思路")
                // 作者信息
                //.contact(new Contact("lin", "https://www.baidu.com/", "354073878@qq.com"))
                .version("1.0.0")
                .build();
    }

}