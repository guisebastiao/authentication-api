package br.com.guisebastiao.authenticationapi.domain.exception;

import br.com.guisebastiao.authenticationapi.domain.enums.DomainErrorCode;

public class AccountActivationResendNotAvailableException extends DomainException {
    public AccountActivationResendNotAvailableException() {
        super(DomainErrorCode.ACCOUNT_ACTIVATION_RESEND_NOT_AVAILABLE);
    }
}
