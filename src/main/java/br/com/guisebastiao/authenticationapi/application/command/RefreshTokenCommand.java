package br.com.guisebastiao.authenticationapi.application.command;

public record RefreshTokenCommand(
        String accessToken,
        String refreshToken,
        String sessionToken
) {
}
