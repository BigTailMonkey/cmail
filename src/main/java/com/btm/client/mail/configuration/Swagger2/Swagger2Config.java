package com.btm.client.mail.configuration.Swagger2;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @Author: BigTailMonkey
 * @Date: 2019/6/24 18:15
 * @Version: 1.0
 */
@Configuration
@EnableSwagger2
public class Swagger2Config {

    @Bean
    public Docket createRestApi(){
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(createApiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.btm.client.mail.controller"))
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo createApiInfo(){
        return new ApiInfoBuilder().title("出门证系统接口。")
                .description("MVM系统接口")
                .version("1.0")
                .build();
    }
}
