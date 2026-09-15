package br.com.guisebastiao.authenticationapi.domain.exception;

import br.com.guisebastiao.authenticationapi.domain.enums.DomainErrorCode;

public class DeviceIdentifierInvalidException extends DomainException {
    public DeviceIdentifierInvalidException() {
        super(DomainErrorCode.DEVICE_IDENTIFIER_INVALID);
    }
}
