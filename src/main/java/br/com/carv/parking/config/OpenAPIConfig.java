package br.com.carv.parking.config;


import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenAPIConfig {

    @Bean
    OpenAPI configOpenApi() {
        return new OpenAPI()
                .components(new Components().addSecuritySchemes("security", this.securityScheme()))
                .info(new Info()
                .title("Parking API")
                .version("1.0 - Development.")
                .description("Parking API System.")
                .contact(new Contact()
                        .name("João Gabriel Carvalho")
                        .email("27.joaogabriel@gmail.com")
                        .url("http://github.com/JoaoGabrielCarvalhoL"))
                .license(new License()
                        .name("Apache 2.0")
                        .url("https://www.apache.org/licenses/LICENSE-2.0.html")));
    }

    private SecurityScheme securityScheme() {
        return new SecurityScheme().description("Insert Bearer Token Valid")
                .type(SecurityScheme.Type.HTTP)
                .in(SecurityScheme.In.HEADER)
                .scheme("bearer")
                .bearerFormat("JWT")
                .name("security");
    }
}
