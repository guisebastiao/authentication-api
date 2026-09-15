package br.com.guisebastiao.authenticationapi.domain.exception;

import br.com.guisebastiao.authenticationapi.domain.enums.DomainErrorCode;

public class AccountActivationAlreadyCompletedException extends DomainException {
    public AccountActivationAlreadyCompletedException() {
        super(DomainErrorCode.ACCOUNT_ACTIVATION_ALREADY_COMPLETED);
    }
}
