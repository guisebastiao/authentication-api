package br.com.guisebastiao.authenticationapi.application.port.out;

import br.com.guisebastiao.authenticationapi.domain.model.Account;

public interface JwtTokenPort {
    String generate(Account account, String refreshToken, String sessionToken);
    JwtValidationStatus validate(String accessToken, String refreshToken, String sessionToken);

    enum JwtValidationStatus {
        VALID,
        EXPIRED,
        INVALID_SIGNATURE,
        INVALID_ISSUER,
        MALFORMED,
        INVALID
    }
}
