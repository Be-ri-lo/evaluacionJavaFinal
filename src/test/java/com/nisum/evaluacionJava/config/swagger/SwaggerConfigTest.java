package com.nisum.evaluacionJava.config.swagger;

import org.junit.jupiter.api.Test;
import org.springframework.boot.info.BuildProperties;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spring.web.plugins.Docket;
import java.util.Properties;
import static org.junit.jupiter.api.Assertions.*;

class SwaggerConfigTest {

    @Test
    public void test_config(){
        Properties props = new Properties();
        BuildProperties buildProperties = new BuildProperties(props);
        assertEquals(Docket.class, new SwaggerConfig(buildProperties).api().getClass() );
        ApiInfo apiInfo = new ApiInfoBuilder()
                .title("Evaluación Java")
                .description("Ejercicio Práctico")
                .termsOfServiceUrl("")
                .license("")
                .licenseUrl("")
                .version(buildProperties.getVersion())
                .build();
        assertEquals(ApiInfo.class, new SwaggerConfig(buildProperties).apiInfo().getClass());
        assertEquals(apiInfo.getContact(), new SwaggerConfig(buildProperties).apiInfo().getContact());

    }
}