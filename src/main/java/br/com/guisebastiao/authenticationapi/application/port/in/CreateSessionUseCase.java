package br.com.guisebastiao.authenticationapi.application.port.in;

import br.com.guisebastiao.authenticationapi.domain.model.Account;
import br.com.guisebastiao.authenticationapi.domain.model.Session;

public interface CreateSessionUseCase {
    CreateSessionResult execute(Account account, String userAgent, String ipAddress);

    record CreateSessionResult(
            String sessionToken,
            Session session
    ) {}
}
