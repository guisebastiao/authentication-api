package br.com.guisebastiao.authenticationapi.domain.exception;

import br.com.guisebastiao.authenticationapi.domain.enums.DomainErrorCode;

public class DomainException extends RuntimeException {
    private final DomainErrorCode code;

    public DomainException(DomainErrorCode code) {
        super(code.name());
        this.code = code;
    }

    public DomainException(DomainErrorCode code, Throwable cause) {
        super(code.name(), cause);
        this.code = code;
    }

    public DomainErrorCode getCode() {
        return code;
    }
}
