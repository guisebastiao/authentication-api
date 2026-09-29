package br.com.guisebastiao.authenticationapi.application.result;

import br.com.guisebastiao.authenticationapi.domain.model.Session;

public record CreateSessionResult(
        String sessionToken,
        Session session
) {
}
