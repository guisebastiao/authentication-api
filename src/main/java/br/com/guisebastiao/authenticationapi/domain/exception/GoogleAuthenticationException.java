package br.com.guisebastiao.authenticationapi.domain.exception;

import br.com.guisebastiao.authenticationapi.domain.enums.DomainErrorCode;

public class GoogleAuthenticationException extends DomainException {
    public GoogleAuthenticationException(Throwable cause) {
        super(
                DomainErrorCode.GOOGLE_AUTHENTICATION_FAILED,
                "Authentication with Google failed.",
                502,
                cause

        );
    }
}
