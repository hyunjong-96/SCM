package com.scm.api.exception;


import lombok.Builder;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ProblemDetail;
import org.springframework.web.ErrorResponse;

import java.time.LocalDateTime;

/**
 * packageName    : com.scm.api.exception
 * fileName       : ErrorResponse
 * author         : leehyunjong
 * date           : 2024/11/17
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024/11/17        leehyunjong       최초 생성
 */
public class ErrorResponseImpl implements ErrorResponse {
    private HttpStatusCode statusCode;
    private ProblemDetail body;
    private LocalDateTime time;
    private String message;

    @lombok.Builder
    public ErrorResponseImpl(Exception ex, ProblemDetail body) {
        this.statusCode = HttpStatusCode.valueOf(body.getStatus());
        this.time = LocalDateTime.now();
        this.body = body;
        this.message = ex.getLocalizedMessage();
    }


    @Override
    public HttpStatusCode getStatusCode() {
        return statusCode;
    }

    @Override
    public ProblemDetail getBody() {
        return body;
    }
}
