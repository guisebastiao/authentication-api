package br.com.guisebastiao.authenticationapi.application.port.in;

import br.com.guisebastiao.authenticationapi.application.command.CreateRecoverPasswordCommand;
import br.com.guisebastiao.authenticationapi.application.result.RecoverPasswordResult;

public interface CreateRecoverPasswordUseCase {
    RecoverPasswordResult execute(CreateRecoverPasswordCommand command);
}
