package com.yt.config;

import com.github.xiaoymin.knife4j.spring.annotations.EnableKnife4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import springfox.bean.validators.configuration.BeanValidatorPluginsConfiguration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @Author zhangyu
 * @Date 2020/6/15
 * @Description
 **/

@Configuration
@EnableSwagger2
@EnableKnife4j
@Import(BeanValidatorPluginsConfiguration.class)
@ConditionalOnProperty(value = {"knife4j.enable"}, matchIfMissing = true)
public class SwaggerConfig {

    /**
     * userAPI分组
     *
     * @return
     */
    @Bean(value = "userApi")
    public Docket indexApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("userAPI分组")
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.yt"))
                .paths(PathSelectors.any())
                .build();
    }

    /**
     * productAPI分组
     *
     * @return
     */
    @Bean(value = "productApi")
    public Docket adminApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("productAPI分组")
                .apiInfo(apiInfo2())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.yt"))
                .paths(PathSelectors.any())
                .build();
    }


    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("swagger-bootstrap-ui RESTful APIs")
                .description("swagger-bootstrap-ui")
                .termsOfServiceUrl("http://localhost:9105/")
                .version("1.0")
                .build();
    }
    private ApiInfo apiInfo2() {
        return new ApiInfoBuilder()
                .title("swagger-bootstrap-ui RESTful APIs")
                .description("swagger-bootstrap-ui")
                .termsOfServiceUrl("http://localhost:9110/")
                .version("1.0")
                .build();
    }

}

