package br.com.guisebastiao.authenticationapi.application.port.in;

import br.com.guisebastiao.authenticationapi.application.command.ValidateRecoverPasswordCommand;
import br.com.guisebastiao.authenticationapi.application.result.ValidateRecoverPasswordResult;

public interface ValidateRecoverPasswordUseCase {
    ValidateRecoverPasswordResult execute(ValidateRecoverPasswordCommand command, String ipAddress);
}
