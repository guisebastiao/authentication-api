package br.com.guisebastiao.authenticationapi.application.command;

public record CreateRecoverPasswordCommand(
        String email
) {
}
