package br.com.guisebastiao.authenticationapi.application.port.in;

import br.com.guisebastiao.authenticationapi.application.result.SessionResult;
import br.com.guisebastiao.authenticationapi.domain.model.Account;

import java.util.List;

public interface GetSessionsUseCase {
    List<SessionResult> execute(Account account, String currentSession);
}
