package br.com.guisebastiao.authenticationapi.application.port.in;

import br.com.guisebastiao.authenticationapi.application.command.AccountActivateCommand;

public interface AccountActivateUseCase {
    void execute(AccountActivateCommand command, String ipAddress);
}
