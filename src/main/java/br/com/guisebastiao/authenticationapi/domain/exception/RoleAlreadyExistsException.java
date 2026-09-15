package br.com.guisebastiao.authenticationapi.domain.exception;

import br.com.guisebastiao.authenticationapi.domain.enums.DomainErrorCode;

public class RoleAlreadyExistsException extends DomainException {
    public RoleAlreadyExistsException() {
        super(DomainErrorCode.ROLE_ALREADY_EXISTS);
    }
}
