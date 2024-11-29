package com.scm.api.auth.handler;

import com.scm.api.auth.model.AccountDetails;
import com.scm.api.auth.provider.JwtAuthorizationProvider;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import java.io.IOException;

@RequiredArgsConstructor
@Slf4j
public class LoginSuccessHandler implements AuthenticationSuccessHandler {
    private final JwtAuthorizationProvider jwtAuthorizationProvider;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        AccountDetails userDetails = (AccountDetails) authentication.getPrincipal();
        log.info( "로그인 성공. JWT 발급. username: {}" ,userDetails.getUsername());

        //todo : 로그인 성공 후 JWT 발급 후 성공 화면으로 redirect할 부분.
        String accessToken = jwtAuthorizationProvider.generateToken(userDetails.getUsername());

        response.setHeader("accessToken", accessToken);
        response.sendRedirect("localhost:8080/dashboard");
        response.getWriter().write("success");
    }
}
