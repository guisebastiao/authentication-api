package br.com.guisebastiao.authenticationapi.application.port.in;

import br.com.guisebastiao.authenticationapi.application.command.DisableAccountCommand;
import br.com.guisebastiao.authenticationapi.domain.model.Account;

public interface DisableAccountUseCase {
    void execute(Account account, DisableAccountCommand command);
}
