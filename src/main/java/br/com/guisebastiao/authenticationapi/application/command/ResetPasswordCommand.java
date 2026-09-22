package br.com.guisebastiao.authenticationapi.application.command;

public record ResetPasswordCommand(
        String recoverToken,
        String newPassword,
        String confirmPassword
) {
}
