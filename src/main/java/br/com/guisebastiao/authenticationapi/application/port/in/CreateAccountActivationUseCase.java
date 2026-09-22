package br.com.guisebastiao.authenticationapi.application.port.in;

import br.com.guisebastiao.authenticationapi.application.result.AccountActivationResult;
import br.com.guisebastiao.authenticationapi.domain.model.Account;

public interface CreateAccountActivationUseCase {
    AccountActivationResult execute(Account account, String ipAddress);
}
