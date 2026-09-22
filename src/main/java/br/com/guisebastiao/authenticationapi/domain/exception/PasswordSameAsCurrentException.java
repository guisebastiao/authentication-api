package br.com.guisebastiao.authenticationapi.domain.exception;

import br.com.guisebastiao.authenticationapi.domain.enums.DomainErrorCode;

public class PasswordSameAsCurrentException extends DomainException {
    public PasswordSameAsCurrentException() {
        super(DomainErrorCode.SAME_PASSWORD);
    }
}
