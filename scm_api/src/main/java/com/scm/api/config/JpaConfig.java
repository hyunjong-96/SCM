package com.scm.api.config;

import com.domain.config.AccountJpaConfig;
import com.domain.config.MergeRequestJpaConfig;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import({AccountJpaConfig.class, MergeRequestJpaConfig.class})
public class JpaConfig {
}
