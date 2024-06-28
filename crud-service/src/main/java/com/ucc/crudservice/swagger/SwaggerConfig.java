package com.ucc.crudservice.swagger;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;
import java.io.InputStream;

@Configuration
@OpenAPIDefinition
public class SwaggerConfig {

    @Bean
    public OpenAPI api() throws IOException {
        // Leer el archivo externo swagger.json
        InputStream inputStream = getClass().getClassLoader().getResourceAsStream("swagger.json");
        if (inputStream == null) {
            throw new IOException("No se encontró el archivo swagger.json en el classpath");
        }

        ObjectMapper objectMapper = new ObjectMapper();
        OpenAPI openAPI = objectMapper.readValue(inputStream, OpenAPI.class);

        // Agregar la información de contacto y demás detalles
        openAPI.info(new Info()
                .title("Nuestra primera API con Java-Spring")
                .version("1")
                .contact(new Contact().name("Maxi").url("https://www.linkedin.com/in/maximiliano-ferrez/"))
                .description("Esta API permite crear un CRUD de Producto")
        );

        return openAPI;
    }
}
