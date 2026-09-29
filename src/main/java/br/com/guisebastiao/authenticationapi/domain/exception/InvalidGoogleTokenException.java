package br.com.guisebastiao.authenticationapi.domain.exception;

import br.com.guisebastiao.authenticationapi.domain.enums.DomainErrorCode;

public class InvalidGoogleTokenException extends DomainException {
    public InvalidGoogleTokenException() {
        super(
                DomainErrorCode.GOOGLE_TOKEN_INVALID,
                "The Google identity token is invalid.",
                400
        );
    }
}
