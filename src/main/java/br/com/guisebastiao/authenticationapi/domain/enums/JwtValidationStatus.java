package br.com.guisebastiao.authenticationapi.domain.enums;

public enum JwtValidationStatus {
    VALID,
    EXPIRED,
    INVALID_SIGNATURE,
    INVALID_ALGORITHM,
    INVALID_ISSUER,
    INVALID
}
