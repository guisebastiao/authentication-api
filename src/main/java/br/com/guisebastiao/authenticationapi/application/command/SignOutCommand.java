package br.com.guisebastiao.authenticationapi.application.command;

public record SignOutCommand(
        String sessionToken
) {
}
