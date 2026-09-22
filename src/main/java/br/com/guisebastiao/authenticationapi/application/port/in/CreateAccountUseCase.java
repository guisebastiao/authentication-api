package br.com.guisebastiao.authenticationapi.application.port.in;

import br.com.guisebastiao.authenticationapi.application.command.CreateAccountCommand;
import br.com.guisebastiao.authenticationapi.application.result.AccountActivationResult;

public interface CreateAccountUseCase {
    AccountActivationResult execute(CreateAccountCommand command, String ipAddress);
}
