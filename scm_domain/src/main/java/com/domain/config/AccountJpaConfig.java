package com.domain.config;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EntityScan(basePackages = "com.domain.account")
@EnableJpaRepositories(basePackages = "com.domain.account.repository")
public class AccountJpaConfig {
}
