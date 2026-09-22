package br.com.guisebastiao.authenticationapi.application.port.in;

import br.com.guisebastiao.authenticationapi.application.command.ChangePasswordCommand;
import br.com.guisebastiao.authenticationapi.domain.model.Account;

public interface ChangePasswordUseCase {
    void execute(Account account, ChangePasswordCommand command);
}
