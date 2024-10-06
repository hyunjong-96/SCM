package com.scm.api;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"com.scm.api", "com.domain"})
public class ScmApplication {
    public static void main(String[] args) {
        new SpringApplicationBuilder(ScmApplication.class).run(args);
    }
}