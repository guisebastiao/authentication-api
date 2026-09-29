package br.com.guisebastiao.authenticationapi.domain.exception;

import br.com.guisebastiao.authenticationapi.domain.enums.DomainErrorCode;

public class RecoverPasswordNotFoundException extends DomainException {
    public RecoverPasswordNotFoundException() {
        super(
                DomainErrorCode.RECOVER_PASSWORD_NOT_FOUND,
                "The password recovery request could not be found.",
                404
        );
    }
}
