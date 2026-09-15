package br.com.guisebastiao.authenticationapi.domain.exception;

import br.com.guisebastiao.authenticationapi.domain.enums.DomainErrorCode;

public class RefreshTokenNotFoundException extends DomainException {
    public RefreshTokenNotFoundException() {
        super(DomainErrorCode.REFRESH_TOKEN_NOT_FOUND);
    }
}
