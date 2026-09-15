package br.com.guisebastiao.authenticationapi.domain.exception;

import br.com.guisebastiao.authenticationapi.domain.enums.DomainErrorCode;

public class DeviceNotLinkedToAccountException extends DomainException {
    public DeviceNotLinkedToAccountException() {
        super(DomainErrorCode.DEVICE_NOT_LINKED_TO_ACCOUNT);
    }
}
