package br.com.guisebastiao.authenticationapi.application.port.in;

import br.com.guisebastiao.authenticationapi.domain.model.Account;

import java.util.List;
import java.util.UUID;

public interface SessionSignOutUseCase {
    void execute(Account account, List<UUID> sessionsIds);
}
