package br.com.guisebastiao.authenticationapi.application.port.in;

import br.com.guisebastiao.authenticationapi.application.command.GoogleSignInCommand;
import br.com.guisebastiao.authenticationapi.application.result.AuthResult;

public interface GoogleSignUpUseCase {
    AuthResult execute(GoogleSignInCommand command, String userAgent, String ipAddress);
}
