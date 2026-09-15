package br.com.guisebastiao.authenticationapi.domain.exception;

import br.com.guisebastiao.authenticationapi.domain.enums.DomainErrorCode;

public class RefreshTokenAlreadyUsedException extends DomainException {
    public RefreshTokenAlreadyUsedException() {
        super(DomainErrorCode.REFRESH_TOKEN_ALREADY_USED);
    }
}
