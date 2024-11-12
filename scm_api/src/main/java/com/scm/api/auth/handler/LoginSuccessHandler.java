package com.scm.api.auth.handler;

import com.domain.account.models.Account;
import com.scm.api.auth.model.AccountDetails;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import java.io.IOException;

@Slf4j
public class LoginSuccessHandler implements AuthenticationSuccessHandler {
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        AccountDetails userDetails = (AccountDetails) authentication.getPrincipal();
        log.info( "로그인 성공. JWT 발급. username: {}" ,userDetails.getUsername());


        response.getWriter().write("success");
    }
}
