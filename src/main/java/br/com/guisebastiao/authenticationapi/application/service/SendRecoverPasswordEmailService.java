package br.com.guisebastiao.authenticationapi.application.service;

import br.com.guisebastiao.authenticationapi.application.port.in.SendRecoverPasswordEmailUseCase;
import br.com.guisebastiao.authenticationapi.application.port.out.EmailSenderPort;

import java.time.Instant;
import java.util.Map;

public class SendRecoverPasswordEmailService implements SendRecoverPasswordEmailUseCase {
    private static final String TEMPLATE = "recover-password-template";

    private final EmailSenderPort emailSender;

    public SendRecoverPasswordEmailService(EmailSenderPort emailSender) {
        this.emailSender = emailSender;
    }

    @Override
    public void execute(String email, String otpCode, Instant expiresAt) {
        String subject = "Solicitação de redefinição de senha";

        Map<String, Object> variables = Map.of(
                "email", email,
                "otpCode", otpCode,
                "expiresAt", expiresAt
        );

        emailSender.send(TEMPLATE, email, subject, variables);
    }
}
