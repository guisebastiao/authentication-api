package br.com.guisebastiao.authenticationapi.domain.exception;

import br.com.guisebastiao.authenticationapi.domain.enums.DomainErrorCode;

public class PasswordSameAsCurrentException extends DomainException {
    public PasswordSameAsCurrentException() {
        super(
                DomainErrorCode.SAME_PASSWORD,
                "The new password matches the current password.",
                401
        );
    }
}
