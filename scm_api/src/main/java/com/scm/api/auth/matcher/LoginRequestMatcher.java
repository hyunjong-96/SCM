package com.scm.api.auth.matcher;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;

import java.util.ArrayList;
import java.util.List;

public class LoginRequestMatcher implements RequestMatcher {

    private final String DEFAULT_LOGIN_HTTP_METHOD = "POST";
    private List<AntPathRequestMatcher> matcherList = new ArrayList<>();

    public LoginRequestMatcher(String ...patterns) {
        for(String pattern : patterns) {
            matcherList.add(new AntPathRequestMatcher(pattern, DEFAULT_LOGIN_HTTP_METHOD));
        }
    }

    @Override
    public boolean matches(HttpServletRequest request) {
        return matcherList.stream().anyMatch(matcher -> matcher.matches(request));
    }
}
