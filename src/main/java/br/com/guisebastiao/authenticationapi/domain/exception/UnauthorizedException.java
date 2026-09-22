package br.com.guisebastiao.authenticationapi.domain.exception;

import br.com.guisebastiao.authenticationapi.domain.enums.DomainErrorCode;

public class UnauthorizedException extends DomainException {
    public UnauthorizedException() {
        super(DomainErrorCode.UNAUTHORIZED);
    }
}
