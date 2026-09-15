package br.com.guisebastiao.authenticationapi.domain.exception;

import br.com.guisebastiao.authenticationapi.domain.enums.DomainErrorCode;

public class SessionRevokedException extends DomainException {
    public SessionRevokedException() {
        super(DomainErrorCode.SESSION_REVOKED);
    }
}
