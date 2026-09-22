package br.com.guisebastiao.authenticationapi.application.port.in;

import br.com.guisebastiao.authenticationapi.domain.model.Account;
import br.com.guisebastiao.authenticationapi.domain.model.Session;

public interface ValidateSessionUseCase {
    Session execute(Account account, String sessionToken);
}
