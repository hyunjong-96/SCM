package com.scm.api.auth.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletInputStream;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.RequestMatcher;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.util.Map;

@Slf4j
public class AuthCustomFilter extends AbstractAuthenticationProcessingFilter {

    private final String EmailKey = "email";
    private final String PasswordKey = "password";
    private final String CONTENT_TYPE = "application/json";
    private final ObjectMapper objectMapper;
    public AuthCustomFilter(RequestMatcher matcher) {
        super(matcher);
        objectMapper = new ObjectMapper();
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException, IOException, ServletException {

        if(request.getContentType() == null || !StringUtils.pathEquals(request.getContentType(), CONTENT_TYPE)) {
            throw new AuthenticationServiceException("Authentication Content-Type not supported: " + request.getContentType());
        }

        ServletInputStream inputStream = request.getInputStream();
        Map<String, String> usernamePasswordMap = objectMapper.readValue(inputStream, Map.class);

        String email = usernamePasswordMap.get(EmailKey);
        String password = usernamePasswordMap.get(PasswordKey);

        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(email, password);

        return this.getAuthenticationManager().authenticate(authentication);
    }
}
