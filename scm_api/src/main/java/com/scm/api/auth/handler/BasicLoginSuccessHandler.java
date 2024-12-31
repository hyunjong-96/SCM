package com.scm.api.auth.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.scm.api.auth.provider.JwtAuthorizationProvider;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * packageName     : com.scm.api.auth.handler
 * fileName       : BasicLoginSuccessHandler
 * author         : leehyunjong
 * date           : 2024-12-30
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-12-30        leehyunjong       최초 생성
 */
@Slf4j
@Component
public class BasicLoginSuccessHandler extends LoginSuccessHandler implements AuthenticationSuccessHandler {


    public BasicLoginSuccessHandler(JwtAuthorizationProvider jwtAuthorizationProvider) {
        super(jwtAuthorizationProvider);
    }


    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {

        String redirectURL = super.getRedirectURL(response, authentication);

        Map<String, String> responseBody = new HashMap<>();
        responseBody.put("redirectUrl", redirectURL);

        response.setContentType("application/json;charset=UTF-8");
        response.getWriter().write(new ObjectMapper().writeValueAsString(responseBody));
    }
}
