package br.com.guisebastiao.authenticationapi.domain.exception;

import br.com.guisebastiao.authenticationapi.domain.enums.DomainErrorCode;

public class DeviceNotFoundException extends DomainException {
    public DeviceNotFoundException() {
        super(DomainErrorCode.DEVICE_NOT_FOUND);
    }
}
