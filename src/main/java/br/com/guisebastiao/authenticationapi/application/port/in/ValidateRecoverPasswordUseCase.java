package br.com.guisebastiao.authenticationapi.application.port.in;

import br.com.guisebastiao.authenticationapi.application.command.ValidateRecoverPasswordCommand;
import br.com.guisebastiao.authenticationapi.application.result.RecoverPasswordResult;

public interface ValidateRecoverPasswordUseCase {
    RecoverPasswordResult execute(ValidateRecoverPasswordCommand command);
}
