package com.scm.api.auth.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.scm.api.auth.model.PrincipalDetails;
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
@RequiredArgsConstructor
@Component
public class BasicLoginSuccessHandler implements AuthenticationSuccessHandler {

    @Value("${login.success.url}")
    protected String redirectURL;

    private final JwtAuthorizationProvider jwtAuthorizationProvider;

//    public BasicLoginSuccessHandler(JwtAuthorizationProvider jwtAuthorizationProvider) {
//        super(jwtAuthorizationProvider);
//    }

    public String getRedirectURL(HttpServletResponse response, Authentication authentication) {
        PrincipalDetails principalDetails = (PrincipalDetails) authentication.getPrincipal();

        log.info( "Success login. JWT 발급. username: {}" ,principalDetails.getEmail());

        //todo : 로그인 성공 후 JWT 발급 후 성공 화면으로 redirect할 부분.
        String accessToken = jwtAuthorizationProvider.generateToken(String.valueOf(principalDetails.getEmail()));

        String redirectUrl = UriComponentsBuilder.fromUriString(redirectURL)
                .queryParam("scm-token", accessToken)
                .build()
                .toString();

        return redirectUrl;
    }


    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {

        String redirectURL = this.getRedirectURL(response, authentication);

        Map<String, String> responseBody = new HashMap<>();
        responseBody.put("redirectUrl", redirectURL);

        response.setContentType("application/json;charset=UTF-8");
        response.getWriter().write(new ObjectMapper().writeValueAsString(responseBody));
    }
}
