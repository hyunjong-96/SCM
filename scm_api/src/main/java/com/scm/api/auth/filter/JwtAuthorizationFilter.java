package com.scm.api.auth.filter;

import com.scm.api.auth.provider.JwtAuthorizationProvider;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

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
@RequiredArgsConstructor
@Slf4j
public class JwtAuthorizationFilter extends OncePerRequestFilter {
    private final JwtAuthorizationProvider jwtAUthorizationProvider;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        /**
         * 1. Header에서 토큰 뽑아오기
         * 2. 토큰 존재 여부 확인
         * 3. provider호출
         * 4. successHandler, failHandler 분기
         */

        try{
            String token = parseJwt(request);

            if(token != null && jwtAUthorizationProvider.validateToken(token)) {
                Authentication authentication = jwtAUthorizationProvider.authentication(token);
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }

            filterChain.doFilter(request, response);

        }
        catch (Exception ex) {
            //fail handler
        }
    }

    private String parseJwt(HttpServletRequest request) {
        String token = request.getHeader("Authorization");

        if(StringUtils.hasText(token) && token.startsWith("Bearer ")) {
            return token.substring(7);
        }

        return null;
    }
}
