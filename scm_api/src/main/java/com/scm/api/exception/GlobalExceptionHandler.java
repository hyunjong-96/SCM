package com.scm.api.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

/**
 * packageName    : com.scm.api.exception
 * fileName       : GlobalExceptionHandler
 * author         : leehyunjong
 * date           : 2024/11/17
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024/11/17        leehyunjong       최초 생성
 */
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<CustomErrorResponse> handleException(Exception ex) {
      ex.printStackTrace();

      CustomErrorResponse errorResponse = CustomErrorResponse.builder()
              .ex(ex)
              .statusCode(HttpStatus.BAD_REQUEST)
              .build();

      return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);

//      ProblemDetail body  = createProblemDetail(ex, HttpStatus.BAD_REQUEST, ex.getMessage(), null, new Object[]{}, null);
//
//      ErrorResponse errorResponse = ErrorResponseImpl.builder()
//              .ex(ex)
//              .body(body)
//              .build();
//      return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(GlobalException.class)
    public ResponseEntity<CustomErrorResponse> handleGlobalException(GlobalException ex) {
        ex.printStackTrace();

        CustomErrorResponse errorResponse = CustomErrorResponse.builder()
                .ex(ex)
                .statusCode(HttpStatus.BAD_REQUEST)
                .build();

        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }
}
