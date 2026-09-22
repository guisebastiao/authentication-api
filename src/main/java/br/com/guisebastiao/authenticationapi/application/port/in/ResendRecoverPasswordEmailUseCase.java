package br.com.guisebastiao.authenticationapi.application.port.in;

public interface ResendRecoverPasswordEmailUseCase {
    void execute(String email, String ipAddress);
}
