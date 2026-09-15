package br.com.guisebastiao.authenticationapi.domain.exception;

import br.com.guisebastiao.authenticationapi.domain.enums.DomainErrorCode;

public class RecoverPasswordAlreadyUsedException extends DomainException {
    public RecoverPasswordAlreadyUsedException() {
        super(DomainErrorCode.RECOVER_PASSWORD_ALREADY_USED);
    }
}
