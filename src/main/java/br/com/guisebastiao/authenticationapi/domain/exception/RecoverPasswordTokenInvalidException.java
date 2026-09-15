package br.com.guisebastiao.authenticationapi.domain.exception;

import br.com.guisebastiao.authenticationapi.domain.enums.DomainErrorCode;

public class RecoverPasswordTokenInvalidException extends DomainException {
    public RecoverPasswordTokenInvalidException() {
        super(DomainErrorCode.RECOVER_PASSWORD_TOKEN_INVALID);
    }
}
