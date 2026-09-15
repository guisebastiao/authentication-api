package br.com.guisebastiao.authenticationapi.domain.exception;

import br.com.guisebastiao.authenticationapi.domain.enums.DomainErrorCode;

public class RoleAlreadyAssignedToAccountException extends DomainException {
    public RoleAlreadyAssignedToAccountException() {
        super(DomainErrorCode.ROLE_ALREADY_ASSIGNED_TO_ACCOUNT);
    }
}
