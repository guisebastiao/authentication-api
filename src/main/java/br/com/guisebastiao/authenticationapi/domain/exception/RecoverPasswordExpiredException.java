package br.com.guisebastiao.authenticationapi.domain.exception;

import br.com.guisebastiao.authenticationapi.domain.enums.DomainErrorCode;

public class RecoverPasswordExpiredException extends DomainException {
    public RecoverPasswordExpiredException() {
        super(DomainErrorCode.RECOVER_PASSWORD_EXPIRED);
    }
}
