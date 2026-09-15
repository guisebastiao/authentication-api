package br.com.guisebastiao.authenticationapi.domain.exception;

import br.com.guisebastiao.authenticationapi.domain.enums.DomainErrorCode;

public class RoleNotFoundException extends DomainException {
    public RoleNotFoundException() {
        super(DomainErrorCode.ROLE_NOT_FOUND);
    }
}
