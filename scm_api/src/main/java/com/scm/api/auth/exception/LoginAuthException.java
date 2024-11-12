package com.scm.api.auth.exception;

import javax.naming.AuthenticationException;

public class LoginAuthException extends AuthException {
    public LoginAuthException(String msg) {
        super(msg, new RuntimeException());
    }

    public LoginAuthException(Throwable throwable) {
        super(throwable.getMessage(), throwable);
    }
}
