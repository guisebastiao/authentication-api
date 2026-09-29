package br.com.guisebastiao.authenticationapi.application.port.in;

import br.com.guisebastiao.authenticationapi.application.result.CreateRefreshResult;
import br.com.guisebastiao.authenticationapi.domain.model.Session;

public interface CreateRefreshUseCase {
    CreateRefreshResult execute(Session session);
}
