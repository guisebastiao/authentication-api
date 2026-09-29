package br.com.guisebastiao.authenticationapi.application.port.in;

import br.com.guisebastiao.authenticationapi.application.command.AccountActivationCommand;

public interface AccountActivateUseCase {
    void execute(AccountActivationCommand command);
}
