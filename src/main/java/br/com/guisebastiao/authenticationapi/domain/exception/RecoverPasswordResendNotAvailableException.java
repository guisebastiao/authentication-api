package br.com.guisebastiao.authenticationapi.domain.exception;

import br.com.guisebastiao.authenticationapi.domain.enums.DomainErrorCode;

public class RecoverPasswordResendNotAvailableException extends DomainException {
    public RecoverPasswordResendNotAvailableException() {
        super(
                DomainErrorCode.RESEND_NOT_AVAILABLE,
                "Password recovery email resend is not currently available.",
                429
        );
    }
}
