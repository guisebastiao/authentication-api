package br.com.guisebastiao.authenticationapi.application.result;

import br.com.guisebastiao.authenticationapi.domain.model.Refresh;

public record CreateRefreshResult(
        String refreshToken,
        Refresh refresh
) {
}
