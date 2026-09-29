package br.com.guisebastiao.authenticationapi.application.command;

public record ValidateRecoverPasswordCommand(
        String recoverToken,
        String otpCode
) {
}
