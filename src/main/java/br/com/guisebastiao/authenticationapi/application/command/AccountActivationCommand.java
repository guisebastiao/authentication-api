package br.com.guisebastiao.authenticationapi.application.command;

public record AccountActivationCommand(
        String activationToken,
        String otpCode
) {
}
