package com.scm.api.config;

import com.scm.api.auth.filter.AuthCustomFilter;
import com.scm.api.auth.filter.JwtAuthorizationFilter;
import com.scm.api.auth.filter.exception.JwtExceptionFilter;
import com.scm.api.auth.handler.*;
import com.scm.api.auth.matcher.LoginRequestMatcher;
import com.scm.api.auth.provider.AuthCustomProvider;
import com.scm.api.auth.provider.JwtAuthorizationProvider;
import com.scm.api.auth.service.AccountDetailService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.RequestMatcher;
import org.springframework.web.cors.CorsConfigurationSource;

@RequiredArgsConstructor
@Configuration
@EnableWebSecurity()
public class SecurityConfig {

    private final AccountDetailService accountDetailService;
    private final CorsConfigurationSource customCorsConfigurationSource;
    private final LoginSuccessHandler loginSuccessHandler;
    private final OAuthLoginSuccessHandler oAuthLoginSuccessHandler;
    private final BasicLoginSuccessHandler basicLoginSuccessHandler;
    private final LoginFailureHandler loginFailureHandler;
    private final OAuth2UserService<OAuth2UserRequest, OAuth2User> customOAuth2UserService;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable)
                .cors(cors -> cors.configurationSource(customCorsConfigurationSource))
                .formLogin(AbstractHttpConfigurer::disable)
                .oauth2Login(oauth2 -> oauth2.userInfoEndpoint(userInfo ->
                                userInfo
                                        .userService(customOAuth2UserService))
                                        .successHandler(oAuthLoginSuccessHandler)
                )
                .addFilterBefore(buildAuthCustomFilter(), UsernamePasswordAuthenticationFilter.class)
                .addFilterBefore(buildJwtAuthorizationFilter(), UsernamePasswordAuthenticationFilter.class)
                .addFilterBefore(buildJwtExceptionFilter(), JwtAuthorizationFilter.class)
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));

        return http.build();
    }


    @Bean
    public AuthCustomFilter buildAuthCustomFilter() {
        RequestMatcher matcher = new LoginRequestMatcher();

        AuthCustomFilter customFilter = new AuthCustomFilter(matcher);
        customFilter.setAuthenticationManager(buildAuthenticationProviderManager());
        customFilter.setAuthenticationSuccessHandler(basicLoginSuccessHandler);
        customFilter.setAuthenticationFailureHandler(loginFailureHandler);

        return customFilter;
    }

    public AuthenticationManager buildAuthenticationProviderManager() {
        return new ProviderManager(buildAuthCustomProvider());
    }

    public AuthenticationProvider buildAuthCustomProvider() {
        return new AuthCustomProvider(accountDetailService, bCryptPasswordEncoder());
    }

    public JwtAuthorizationFilter buildJwtAuthorizationFilter() {
        return new JwtAuthorizationFilter(buildJwtAuthorizationProvider());
    }

    @Bean
    public JwtAuthorizationProvider buildJwtAuthorizationProvider() {
        return new JwtAuthorizationProvider(accountDetailService);
    }

    public JwtExceptionFilter buildJwtExceptionFilter() {
        return new JwtExceptionFilter();
    }

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

//    public AuthenticationSuccessHandler buildSuccessHandler() {
//        return loginSuccessHandler;
//    }

//    public AuthenticationFailureHandler buildFailureHandler() {
//        return loginFailureHandler;
//    }


}
