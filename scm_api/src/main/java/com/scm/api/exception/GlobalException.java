package com.scm.api.exception;

import org.springframework.http.HttpStatus;

/**
 * packageName    : com.scm.api.exception
 * fileName       : GlobalException
 * author         : leehyunjong
 * date           : 2024/11/17
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024/11/17        leehyunjong       최초 생성
 */
public class GlobalException extends Exception{

    String message;

    public GlobalException(Exception ex) {
        super(ex);
        this.message = ex.getMessage();
    }

    public GlobalException(String message) {
        super(message);
        this.message = message;
    }
}
