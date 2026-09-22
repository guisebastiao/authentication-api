package br.com.guisebastiao.authenticationapi.application.port.in;

import br.com.guisebastiao.authenticationapi.application.command.SignInCommand;
import br.com.guisebastiao.authenticationapi.application.result.AuthResult;

public interface SignInUseCase {
    AuthResult execute(SignInCommand command, String userAgent, String ipAddress);
}
