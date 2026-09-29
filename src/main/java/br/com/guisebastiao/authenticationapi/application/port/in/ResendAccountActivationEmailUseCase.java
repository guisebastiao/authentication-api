package br.com.guisebastiao.authenticationapi.application.port.in;

import br.com.guisebastiao.authenticationapi.application.result.AccountActivationResult;

public interface ResendAccountActivationEmailUseCase {
    AccountActivationResult execute(String activationToken);
}
