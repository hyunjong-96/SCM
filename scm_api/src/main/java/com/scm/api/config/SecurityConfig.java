package com.scm.api.config;

import com.scm.api.auth.filter.AuthCustomFilter;
import com.scm.api.auth.handler.LoginFailureHandler;
import com.scm.api.auth.handler.LoginSuccessHandler;
import com.scm.api.auth.matcher.LoginRequestMatcher;
import com.scm.api.auth.provider.AuthCustomProvider;
import com.scm.api.auth.service.AccountDetailService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.RequestMatcher;

@RequiredArgsConstructor
@Configuration
@EnableWebSecurity()
public class SecurityConfig {

    private final AccountDetailService accountDetailService;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable)
                .cors(AbstractHttpConfigurer::disable)
                .formLogin(AbstractHttpConfigurer::disable)
                .addFilterBefore(buildAuthCustomFilter(), UsernamePasswordAuthenticationFilter.class)
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));

        return http.build();
    }

    @Bean
    public AuthCustomFilter buildAuthCustomFilter() {
        RequestMatcher matcher = new LoginRequestMatcher();

        AuthCustomFilter customFilter = new AuthCustomFilter(matcher);
        customFilter.setAuthenticationManager(buildAuthenticationProviderManager());
        customFilter.setAuthenticationSuccessHandler(buildSuccessHandler());
        customFilter.setAuthenticationFailureHandler(buildFailureHandler());

        return customFilter;
    }

    @Bean
    public AuthenticationManager buildAuthenticationProviderManager() {
        return new ProviderManager(buildAuthCustomProvider());
    }

    public AuthenticationProvider buildAuthCustomProvider() {
        return new AuthCustomProvider(accountDetailService, bCryptPasswordEncoder());
    }

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    public AuthenticationSuccessHandler buildSuccessHandler() {
        return new LoginSuccessHandler();
    }

    public AuthenticationFailureHandler buildFailureHandler() {
        return new LoginFailureHandler();
    }

}
