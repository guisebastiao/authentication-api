package br.com.guisebastiao.authenticationapi.application.port.out;

import br.com.guisebastiao.authenticationapi.adapter.out.smtp.EmailSendPaylod;

public interface EmailSenderPort {
    void send(EmailSendPaylod payload);
}
