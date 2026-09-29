package br.com.guisebastiao.authenticationapi.application.port.out;

public interface AuthenticationPort {
    void authenticate(String email, String password);
}
