package br.com.guisebastiao.authenticationapi.domain.exception;

import br.com.guisebastiao.authenticationapi.domain.enums.DomainErrorCode;

public class DeviceAlreadyExistsException extends DomainException {
    public DeviceAlreadyExistsException() {
        super(DomainErrorCode.DEVICE_ALREADY_EXISTS);
    }
}
