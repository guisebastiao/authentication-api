package br.com.guisebastiao.authenticationapi.application.command;

public record SignInCommand(
       String email,
       String password
) {
}
