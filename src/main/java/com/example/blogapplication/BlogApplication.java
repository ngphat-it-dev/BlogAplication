package com.example.blogapplication;

import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication

@OpenAPIDefinition(
        info = @Info(
                title = "Blog Application Java Spring Boot",
                description = "Back-end Test APIs",
                version = "v1.0",
                contact = @Contact(
                        name = "Ngo Gia Phat",
                        email = "phatng.it.dev@gmail.com",
                        url = "https://github.com/ngphat-it-dev/BlogAplication"
                ),
                license = @License(
                        name = "Java Spring Boot",
                        url = "https://github.com/ngphat-it-dev/BlogAplication"
                )
        ),externalDocs = @ExternalDocumentation(
        description = "Blog Application Documentation",
        url = "https://github.com/ngphat-it-dev/BlogAplication"
)
)

public class BlogApplication {
    @Bean
    public ModelMapper modelMapper(){
        return new ModelMapper();
    }
    public static void main(String[] args) {
        SpringApplication.run(BlogApplication.class, args);
    }

}
