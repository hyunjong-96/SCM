package com.scm.api.auth.filter.exception;

import com.scm.api.exception.CustomErrorResponse;
import com.scm.api.exception.JwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

/**
 * packageName     : com.scm.api.auth.filter.exception
 * fileName       : JwtExceptionFilter
 * author         : leehyunjong
 * date           : 2024-12-31
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-12-31        leehyunjong       최초 생성
 */
@Slf4j
public class JwtExceptionFilter extends OncePerRequestFilter {
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        try{
            doFilter(request, response, filterChain);
        }
        catch (JwtException ex) {
            log.info("JwtException Filter");

            CustomErrorResponse errorResponse = CustomErrorResponse.builder()
                    .ex(ex)
                    .statusCode(HttpStatus.FORBIDDEN)
                    .build();

            response.setStatus(HttpStatus.FORBIDDEN.value());
            response.setContentType("application/json");
            response.getWriter().write(errorResponse.toString());
        }
    }
}
