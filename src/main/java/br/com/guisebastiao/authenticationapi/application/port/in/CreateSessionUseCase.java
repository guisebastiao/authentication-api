package br.com.guisebastiao.authenticationapi.application.port.in;

import br.com.guisebastiao.authenticationapi.application.result.CreateSessionResult;
import br.com.guisebastiao.authenticationapi.domain.model.Account;

public interface CreateSessionUseCase {
    CreateSessionResult execute(Account account, String userAgent, String ipAddress);
}
