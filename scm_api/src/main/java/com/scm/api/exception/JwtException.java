package com.scm.api.exception;

/**
 * packageName     : com.scm.api.exception
 * fileName       : JwtException
 * author         : leehyunjong
 * date           : 2024-12-31
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-12-31        leehyunjong       최초 생성
 */
public class JwtException extends RuntimeException{
    String message;

    public JwtException(String message, Throwable ex) {
        super(message, ex);
        this.message = message;
    }

    public JwtException(Throwable ex) {
        super(ex);
        this.message = ex.getMessage();
    }

    public JwtException(String message) {
        super(message);
        this.message = message;
    }
}
