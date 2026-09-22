package br.com.guisebastiao.authenticationapi.domain.exception;

import br.com.guisebastiao.authenticationapi.domain.enums.DomainErrorCode;

public class RecoverPasswordInvalidException extends DomainException {
    public RecoverPasswordInvalidException() {
        super(DomainErrorCode.RECOVER_PASSWORD_INVALID);
    }
}
