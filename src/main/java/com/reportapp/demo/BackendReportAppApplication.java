package com.reportapp.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;

@SpringBootApplication
@OpenAPIDefinition(info = @Info(title = "Report API", version = "1.0", description = "Report management API"))
public class BackendReportAppApplication {


    public static void main(String[] args) {
        SpringApplication.run(BackendReportAppApplication.class, args);
    }

}
