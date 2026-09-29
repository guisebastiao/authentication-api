package br.com.guisebastiao.authenticationapi.application.port.in;

import br.com.guisebastiao.authenticationapi.application.result.AccountResult;
import br.com.guisebastiao.authenticationapi.domain.model.Account;

public interface GetCurrentAccountUseCase {
    AccountResult execute(Account account);
}
