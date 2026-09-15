package br.com.guisebastiao.authenticationapi.domain.exception;

import br.com.guisebastiao.authenticationapi.domain.enums.DomainErrorCode;

public class DeviceAlreadyLinkedToAccountException extends DomainException {
    public DeviceAlreadyLinkedToAccountException() {
        super(DomainErrorCode.DEVICE_ALREADY_LINKED_TO_ACCOUNT);
    }
}
