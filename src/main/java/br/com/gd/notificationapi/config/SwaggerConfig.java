package br.com.gd.notificationapi.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Collections;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Value("${swagger.app.name}")
    private String name;

    @Value("${swagger.app.version}")
    private String version;

    @Value("${swagger.app.description}")
    private String description;

    @Value("${swagger.base.package}")
    private String basePackage;

    @Value("${swagger.contact.name}")
    private String contactName;

    @Value("${swagger.contact.email}")
    private String contactEmail;

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("br.com.gd.notificationapi"))
                .paths(PathSelectors.any())
                .build()
                .apiInfo(getApiInformation())
                .useDefaultResponseMessages(false);
    }

    private ApiInfo getApiInformation(){
        return new ApiInfo(
                name,
                description,
                version,
                "",
                new Contact(contactName, "", contactEmail),
                "",
                "",
                Collections.emptyList()
        );
    }
}
