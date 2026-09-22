package br.com.guisebastiao.authenticationapi.application.port.in;

import br.com.guisebastiao.authenticationapi.application.command.RefreshTokenCommand;
import br.com.guisebastiao.authenticationapi.application.result.AuthResult;
import br.com.guisebastiao.authenticationapi.domain.model.Account;

public interface RefreshTokenUseCase {
    AuthResult execute(Account account, RefreshTokenCommand command);
}
