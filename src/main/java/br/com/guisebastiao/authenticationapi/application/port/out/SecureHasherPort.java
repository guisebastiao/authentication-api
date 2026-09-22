package br.com.guisebastiao.authenticationapi.application.port.out;

public interface SecureHasherPort {
    String hash(String value);
}
