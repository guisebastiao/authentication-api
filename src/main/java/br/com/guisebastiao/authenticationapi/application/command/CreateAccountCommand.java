package br.com.guisebastiao.authenticationapi.application.command;

public record CreateAccountCommand(
       String email,
       String password
) {
}
