package br.com.guisebastiao.authenticationapi.application.service;

import br.com.guisebastiao.authenticationapi.adapter.out.smtp.EmailSendPaylod;
import br.com.guisebastiao.authenticationapi.application.port.in.SendAccountActivationEmailUseCase;
import br.com.guisebastiao.authenticationapi.application.port.out.*;

import java.time.Instant;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.Map;

public class SendAccountActivationEmailService implements SendAccountActivationEmailUseCase {
    private static final String TEMPLATE = "account-activation-template";

    private final EmailSenderPort emailSender;

    public SendAccountActivationEmailService(EmailSenderPort emailSender) {
        this.emailSender = emailSender;
    }

    @Override
    public void execute(String email, String otpCode, Instant expiresAt) {
        String subject = "Confirme seu e-mail para ativar sua conta";

        Map<String, Object> variables = Map.of(
                "email", email,
                "otpCode", otpCode
        );

        emailSender.send(new EmailSendPaylod(TEMPLATE, email, subject, variables));
    }
}
