package com.edutech.progressive;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication 
@ComponentScan(basePackages = "com.edutech.progressive")
@EntityScan(basePackages = "com.edutech.progressive.entity")
@EnableJpaRepositories(basePackages = "com.edutech.progressive.repository")
public class EduConnectApplication {
    public static void main(String[] args) {
        System.out.println("Welcome to EduConnect Progressive Project!");
        SpringApplication.run(EduConnectApplication.class, args);
    }
}