package br.com.guisebastiao.authenticationapi.domain.exception;

import br.com.guisebastiao.authenticationapi.domain.enums.DomainErrorCode;

public class RefreshTokenExpiredException extends DomainException {
    public RefreshTokenExpiredException() {
        super(DomainErrorCode.REFRESH_TOKEN_EXPIRED);
    }
}
