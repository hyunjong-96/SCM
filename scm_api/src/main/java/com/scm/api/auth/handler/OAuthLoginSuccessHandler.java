package com.scm.api.auth.handler;

import com.scm.api.auth.model.PrincipalDetails;
import com.scm.api.auth.provider.JwtAuthorizationProvider;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * packageName     : com.scm.api.auth.handler
 * fileName       : LoginOAuthSuccessHandler
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
public class OAuthLoginSuccessHandler extends LoginSuccessHandler implements AuthenticationSuccessHandler {

    public OAuthLoginSuccessHandler(JwtAuthorizationProvider jwtAuthorizationProvider) {
        super(jwtAuthorizationProvider);
    }

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {

        String redirectUrl = super.getRedirectURL(response, authentication);

        response.sendRedirect(redirectUrl);
    }
}
