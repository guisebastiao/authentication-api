package br.com.guisebastiao.authenticationapi.domain.exception;

import br.com.guisebastiao.authenticationapi.domain.enums.DomainErrorCode;

public class RecoverPasswordResendNotAvailableException extends DomainException {
    public RecoverPasswordResendNotAvailableException() {
        super(DomainErrorCode.RECOVER_PASSWORD_RESEND_NOT_AVAILABLE);
    }
}
