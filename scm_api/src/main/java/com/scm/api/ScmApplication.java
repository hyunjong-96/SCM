package com.scm.api;

import com.domain.DomainBasePackage;
//import com.domain.config.AccountJpaConfig;
import com.domain.config.MergeRequestJpaConfig;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;

@SpringBootApplication
public class ScmApplication {
    public static void main(String[] args) {
        new SpringApplicationBuilder(ScmApplication.class)
                .properties(DomainBasePackage.getSpringRunnerProperties())
                .run(args);
    }
}