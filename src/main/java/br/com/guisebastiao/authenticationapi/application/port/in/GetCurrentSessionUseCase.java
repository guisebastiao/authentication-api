package br.com.guisebastiao.authenticationapi.application.port.in;

import br.com.guisebastiao.authenticationapi.application.result.SessionResult;
import br.com.guisebastiao.authenticationapi.domain.model.Account;

public interface GetCurrentSessionUseCase {
    SessionResult execute(Account account, String currentSession);
}
