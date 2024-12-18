package com.scm.api.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * packageName     : com.scm.api.config
 * fileName       : CorsConfig
 * author         : leehyunjong
 * date           : 2024-12-11
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-12-11        leehyunjong       최초 생성
 */
@Configuration
public class CorsConfig {

    private final List<String> AllowOriginList = List.of("http://localhost:8080","http://localhost:8081");

    @Bean(value = "customCorsConfigurationSource")
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowCredentials(true);    // 클라이언트의 자격 증명(쿠키, 헤더)을 포함한 요청 허용(Access-Control-Allow-Credentials: true)
        configuration.setAllowedOrigins(AllowOriginList);   //요청을 허용할 특정 출처(도메인)를 설정
        configuration.setAllowedHeaders(Collections.singletonList("*"));  //클라이언트가 요청할 때 사용할 수 있는 HTTP 헤더를 설정
//        configuration.addAllowedMethod("*");    //HTTP 메서드(GET, POST 등) 중 어떤 메서드가 허용될지를 설정
        configuration.setAllowedMethods(List.of("HEAD","POST","GET","DELETE","PUT"));

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource(); //URL 경로를 기준으로 CORS 설정을 적용
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }
}
