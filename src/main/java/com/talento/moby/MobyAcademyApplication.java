package com.talento.moby;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@EnableWebMvc
@SpringBootApplication
public class MobyAcademyApplication {

    public static void main(String[] args) {
        SpringApplication.run(MobyAcademyApplication.class, args);
    }

}
