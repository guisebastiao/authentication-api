package br.com.guisebastiao.authenticationapi.domain.exception;

import br.com.guisebastiao.authenticationapi.domain.enums.DomainErrorCode;

public class RoleNotAssignedToAccountException extends DomainException {
    public RoleNotAssignedToAccountException() {
        super(DomainErrorCode.ROLE_NOT_ASSIGNED_TO_ACCOUNT);
    }
}
