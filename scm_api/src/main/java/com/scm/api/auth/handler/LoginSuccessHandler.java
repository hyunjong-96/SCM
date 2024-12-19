package com.scm.api.auth.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.scm.api.auth.model.AccountDetails;
import com.scm.api.auth.provider.JwtAuthorizationProvider;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@RequiredArgsConstructor
@Component
public class LoginSuccessHandler implements AuthenticationSuccessHandler {

    @Value("${login.success.url}")
    private String redirectURL;

    private final JwtAuthorizationProvider jwtAuthorizationProvider;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        AccountDetails userDetails = (AccountDetails) authentication.getPrincipal();
        log.info( "로그인 성공. JWT 발급. username: {}" ,userDetails.getUsername());

        //todo : 로그인 성공 후 JWT 발급 후 성공 화면으로 redirect할 부분.
        String accessToken = jwtAuthorizationProvider.generateToken(userDetails.getUsername());

        String redirectUrl = UriComponentsBuilder.fromUriString(redirectURL)
                        .queryParam("scm-token", accessToken)
                                .build()
                .toString();

        Map<String, String> responseBody = new HashMap<>();
        responseBody.put("redirectUrl", redirectUrl);

        response.setContentType("application/json;charset=UTF-8");
        response.getWriter().write(new ObjectMapper().writeValueAsString(responseBody));
    }
}
