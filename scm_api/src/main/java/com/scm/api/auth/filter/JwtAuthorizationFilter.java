package com.scm.api.auth.filter;

import com.scm.api.auth.provider.JwtAuthorizationProvider;
import com.scm.api.exception.JwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.PathMatcher;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.*;

/**
 * packageName    : com.scm.api.auth.filter
 * fileName       : JwtAuthorizationFilter
 * author         : leehyunjong
 * date           : 2024/11/23
 * description    :화
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024/11/23        leehyunjong       최초 생성
 */
@Slf4j
public class JwtAuthorizationFilter extends OncePerRequestFilter {

    private final PathMatcher pathMatcher = new AntPathMatcher();
    private final Set<String> SkipPattern = new HashSet<>(List.of("/auth/**", "/api/auth/**", "/api/account/save","/favicon.ico"));
    private final JwtAuthorizationProvider jwtAUthorizationProvider;

    public JwtAuthorizationFilter(JwtAuthorizationProvider jwtAUthorizationProvider) {
        this.jwtAUthorizationProvider = jwtAUthorizationProvider;
    }

    /**
     * Jwt Filter를 타지 않는 예외 요청 URL 처리
     * @param request current HTTP request
     * @return URL pattern을 만족하는 경우 true -> true이면 Jwt Filter를 타지 않는다.
     */
    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) {
        String requestUrl = request.getRequestURI();
        return SkipPattern.stream().anyMatch(pattern -> pathMatcher.match(pattern, requestUrl));
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        /**
         * 1. Header에서 토큰 뽑아오기
         * 2. 토큰 존재 여부 확인
         * 3. provider호출
         * 4. successHandler, failHandler 분기
         */

        try {
            String token = parseJwt(request);

            if (token == null) {
                throw new JwtException("jwt is null");
            }

            if (!jwtAUthorizationProvider.validateToken(token)) {
                throw new JwtException("invalid jwt");
            }

            Authentication authentication = jwtAUthorizationProvider.authentication(token);
            SecurityContextHolder.getContext().setAuthentication(authentication);

            filterChain.doFilter(request, response);
        } catch (Exception ex) {
            throw new JwtException(ex.getMessage(), ex);
        }
    }

    private String parseJwt(HttpServletRequest request) {
        String token = request.getHeader("Authorization");

        if (StringUtils.hasText(token) && token.startsWith("Bearer ")) {
            return token.substring(7);
        }

        return null;
    }
}
