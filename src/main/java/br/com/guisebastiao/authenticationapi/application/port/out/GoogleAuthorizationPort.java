package br.com.guisebastiao.authenticationapi.application.port.out;

import br.com.guisebastiao.authenticationapi.application.result.GoogleAuthorizationResult;

public interface GoogleAuthorizationPort {
    GoogleAuthorizationResult authorize(String credential);
}
