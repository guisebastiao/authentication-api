package br.com.guisebastiao.authenticationapi.domain.exception;

import br.com.guisebastiao.authenticationapi.domain.enums.DomainErrorCode;

public class RecoverPasswordTokenExpiredException extends DomainException {
    public RecoverPasswordTokenExpiredException() {
        super(DomainErrorCode.RECOVER_PASSWORD_TOKEN_EXPIRED);
    }
}
