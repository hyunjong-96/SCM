package com.domain;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

//@ComponentScan(basePackageClasses = DomainBasePackage.class)
@Configuration
public class DomainBasePackage {

    public static Map<String, Object> getSpringRunnerProperties() {
        Map<String, Object> additionalProperties = new HashMap<>();
        additionalProperties.put("spring.config.location", "classpath:/domain-config/,classpath:/");
        return additionalProperties;
    }

}
