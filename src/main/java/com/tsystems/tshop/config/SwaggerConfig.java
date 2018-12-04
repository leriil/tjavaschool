package com.tsystems.tshop.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import static springfox.documentation.builders.PathSelectors.any;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Bean
    public Docket api(){
        return new Docket(DocumentationType.SWAGGER_2).groupName("Tshop").select()
                .apis(RequestHandlerSelectors.basePackage("com.tsystems.tshop.controllers"))
                .paths(any()).build().apiInfo(new ApiInfo("Tshop",
                        "Online shopping", "1.0.0", null,
                        new Contact("Ekaterina Likhacheva", "https://vk.com/leriilleriil", "leriil@mail.ru"),null, null));
    }


}
