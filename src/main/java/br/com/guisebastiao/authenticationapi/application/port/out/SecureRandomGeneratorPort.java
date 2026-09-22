package br.com.guisebastiao.authenticationapi.application.port.out;

public interface SecureRandomGeneratorPort {
    String generate(int size);
}
