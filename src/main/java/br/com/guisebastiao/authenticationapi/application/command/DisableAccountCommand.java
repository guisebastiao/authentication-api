package br.com.guisebastiao.authenticationapi.application.command;

public record DisableAccountCommand(
        String password
) {
}
