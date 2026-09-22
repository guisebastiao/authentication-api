package br.com.guisebastiao.authenticationapi.application.port.out;

import br.com.guisebastiao.authenticationapi.domain.model.Account;

public interface AuthenticationPort {
    Account authenticate(String email, String password);
}
