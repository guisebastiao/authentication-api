package br.com.guisebastiao.authenticationapi.application.port.in;

import br.com.guisebastiao.authenticationapi.application.command.ResendAccountActivationEmailCommand;
import br.com.guisebastiao.authenticationapi.application.result.AccountActivationResult;

public interface ResendAccountActivationEmailUseCase {
    AccountActivationResult execute(ResendAccountActivationEmailCommand command, String ipAddress);
    AccountActivationResult execute(String activationToken, String ipAddress);
}
