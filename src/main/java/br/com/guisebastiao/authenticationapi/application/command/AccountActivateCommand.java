package br.com.guisebastiao.authenticationapi.application.command;

public record AccountActivateCommand(
        String otpCode,
        String activationToken
) {
}
