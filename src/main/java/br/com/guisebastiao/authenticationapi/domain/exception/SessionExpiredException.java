package br.com.guisebastiao.authenticationapi.domain.exception;

import br.com.guisebastiao.authenticationapi.domain.enums.DomainErrorCode;

public class SessionExpiredException extends DomainException {
    public SessionExpiredException() {
        super(DomainErrorCode.SESSION_EXPIRED);
    }
}
