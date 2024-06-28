package com.MaxiFerrez.crud_orders.swagger;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;

import io.swagger.v3.oas.models.OpenAPI;
import  org.springframework.context.annotation.Bean;
import  org.springframework.context.annotation.Configuration;

import java.io.IOException;
import java.io.InputStream;

@Configuration // es una anotacion que indica le dice que esta clase va ser de configuracion
@OpenAPIDefinition //marca que esta clase va ser usada por swagger para hacer la documentacion


public class SwaggerConfig {
    //un been es un objeto intanciado,esambleado y gesteniona en el contedor de spring
    @Bean //la utilizamos dentro de una clase con metodo configuration par indicar que el retorno este registrado.
    public OpenAPI api() throws IOException {
        InputStream inputStream = getClass().getClassLoader().getResourceAsStream("swaggers.json");
        ObjectMapper objectMapper = new ObjectMapper();
        OpenAPI openAPI = objectMapper.readValue(inputStream, OpenAPI.class);
        return   openAPI.info(openAPI.getInfo());

    }
}