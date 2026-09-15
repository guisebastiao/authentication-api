package br.com.guisebastiao.authenticationapi.domain.exception;

import br.com.guisebastiao.authenticationapi.domain.enums.DomainErrorCode;

public class SessionInvalidException extends DomainException {
    public SessionInvalidException() {
        super(DomainErrorCode.SESSION_INVALID);
    }
}
