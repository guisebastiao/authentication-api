package br.com.guisebastiao.authenticationapi.domain.exception;

import br.com.guisebastiao.authenticationapi.domain.enums.DomainErrorCode;

public class IncorrectOtpException extends DomainException {
    public IncorrectOtpException() {
        super(DomainErrorCode.INCORRECT_OTP);
    }
}
