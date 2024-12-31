package com.exception;

/**
 * packageName     : com.exception
 * fileName       : GlobalDomainException
 * author         : leehyunjong
 * date           : 2024-12-30
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-12-30        leehyunjong       최초 생성
 */
public class GlobalDomainException extends RuntimeException{
    String message;

    public GlobalDomainException(Exception ex) {
        super(ex);
        this.message = ex.getMessage();
    }

    public GlobalDomainException(String message) {
        super(message);
        this.message = message;
    }
}
