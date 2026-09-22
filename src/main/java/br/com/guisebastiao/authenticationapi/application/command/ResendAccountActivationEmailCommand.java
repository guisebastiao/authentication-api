package br.com.guisebastiao.authenticationapi.application.command;

public record ResendAccountActivationEmailCommand(
        String activationToken
) {
}
