package br.com.guisebastiao.authenticationapi.application.command;

public record ValidateRecoverPasswordCommand(
        String email,
        String otpCode
) {
}
