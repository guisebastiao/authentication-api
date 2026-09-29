package br.com.guisebastiao.authenticationapi.application.port.out;

import br.com.guisebastiao.authenticationapi.application.result.JwtValidationResult;
import br.com.guisebastiao.authenticationapi.domain.model.Account;

public interface JwtTokenPort {
    String generate(Account account, String sessionToken);

    JwtValidationResult validate(String accessToken, String sessionToken);
}
