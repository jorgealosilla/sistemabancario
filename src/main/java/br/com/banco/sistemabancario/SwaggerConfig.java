package br.com.banco.sistemabancario;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Bean
    public Docket customImplementation() {
        Docket docket = new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("br.com.banco.sistemabancario"))
                .paths(PathSelectors.any())
                .build();

        return docket.apiInfo(getApiInfo());
    }


    private ApiInfo getApiInfo() {
        return new ApiInfoBuilder()
                .title("Desafio Banco")
                .description("Sistema bancário")
                .version("0.0.1-SNAPSHOT")
                .build();
    }


}
