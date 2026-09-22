package br.com.guisebastiao.authenticationapi.application.port.in;

import br.com.guisebastiao.authenticationapi.domain.model.Account;

public interface SignOutAllUseCase {
    void execute(Account account);
}
