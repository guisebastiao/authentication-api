package br.com.guisebastiao.authenticationapi.application.port.in;

import br.com.guisebastiao.authenticationapi.application.command.CreateRecoverPasswordCommand;

public interface CreateRecoverPasswordUseCase {
    void execute(CreateRecoverPasswordCommand command, String ipAddress);
}
