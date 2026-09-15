package br.com.guisebastiao.authenticationapi.domain.exception;

import br.com.guisebastiao.authenticationapi.domain.enums.DomainErrorCode;

public class SessionNotFoundException extends DomainException {
    public SessionNotFoundException() {
        super(DomainErrorCode.SESSION_NOT_FOUND);
    }
}
