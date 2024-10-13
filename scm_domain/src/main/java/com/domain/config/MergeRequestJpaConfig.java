package com.domain.config;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EntityScan(basePackages = "com.domain.mr")
@EnableJpaRepositories(basePackages = "com.domain.mr.repository")
public class MergeRequestJpaConfig {
}
