package br.com.guisebastiao.authenticationapi.domain.exception;

import br.com.guisebastiao.authenticationapi.domain.enums.DomainErrorCode;

public class RecoverPasswordNotVerifiedException extends DomainException {
    public RecoverPasswordNotVerifiedException() {
        super(
                DomainErrorCode.RECOVER_PASSWORD_NOT_VERIFIED,
                "The password recovery not verified",
                401
        );
    }
}
