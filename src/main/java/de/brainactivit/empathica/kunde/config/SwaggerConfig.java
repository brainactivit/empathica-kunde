package de.brainactivit.empathica.kunde.config;

import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.swagger2.annotations.EnableSwagger2;


@Configuration
@EnableSwagger2
public class SwaggerConfig {

    private ApiInfo apiEndPointsInfo() {
        return new ApiInfoBuilder().title("Empathica REST API")
            .description("Kunde Management REST API")
            .contact(new Contact("BrainActivIT", "www.brain-activit.com", "info@brain-activit.com"))
            .license("not decided yet")
            .licenseUrl("http://www.apache.org/licenses/LICENSE-2.0.html")
            .version("1.0.0")
            .build();
    }
}
