package br.com.guisebastiao.authenticationapi.application.port.out;

import java.util.Map;

public interface EmailSenderPort {
    void send(String template, String recipient, String subject, Map<String, Object> variables);
}
