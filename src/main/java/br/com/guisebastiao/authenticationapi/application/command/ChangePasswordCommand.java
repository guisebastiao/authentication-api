package br.com.guisebastiao.authenticationapi.application.command;

public record ChangePasswordCommand(
        String currentPassword,
        String newPassword,
        String confirmPassword
) {
}
