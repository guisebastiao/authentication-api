package br.com.guisebastiao.authenticationapi.application.port.in;

import br.com.guisebastiao.authenticationapi.domain.model.Refresh;
import br.com.guisebastiao.authenticationapi.domain.model.Session;

public interface CreateRefreshUseCase {
    CreateRefreshResult execute(Session session);
    CreateRefreshResult execute(Session session, Refresh replacedBy);

    record CreateRefreshResult(
            String refreshToken,
            Refresh refresh
    ) {}
}
