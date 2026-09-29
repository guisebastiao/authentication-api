package br.com.guisebastiao.authenticationapi.domain.exception;

import br.com.guisebastiao.authenticationapi.domain.enums.DomainErrorCode;

public class SessionNotBelongToAccountException extends DomainException {
    public SessionNotBelongToAccountException() {
        super(
                DomainErrorCode.SESSION_NOT_BELONG_TO_ACCOUNT,
                "The session does not belong to the requested account.",
                403
        );
    }
}
