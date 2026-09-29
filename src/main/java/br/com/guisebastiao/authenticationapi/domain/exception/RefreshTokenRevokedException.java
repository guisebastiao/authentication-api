package br.com.guisebastiao.authenticationapi.domain.exception;

import br.com.guisebastiao.authenticationapi.domain.enums.DomainErrorCode;

public class RefreshTokenRevokedException extends DomainException {
    public RefreshTokenRevokedException() {
        super(
                DomainErrorCode.REFRESH_TOKEN_REVOKED,
                "The refresh token has been revoked.",
                401
        );
    }
}
