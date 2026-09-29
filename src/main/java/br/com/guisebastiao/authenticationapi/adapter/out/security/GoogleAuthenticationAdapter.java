package br.com.guisebastiao.authenticationapi.adapter.out.security;

import br.com.guisebastiao.authenticationapi.application.port.out.GoogleAuthorizationPort;
import br.com.guisebastiao.authenticationapi.application.result.GoogleAuthorizationResult;
import br.com.guisebastiao.authenticationapi.domain.exception.GoogleAuthenticationException;
import br.com.guisebastiao.authenticationapi.domain.exception.InvalidGoogleTokenException;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.security.GeneralSecurityException;

@Component
@RequiredArgsConstructor
public class GoogleAuthenticationAdapter implements GoogleAuthorizationPort {
    private final GoogleIdTokenVerifier verifier;

    @Override
    public GoogleAuthorizationResult authorize(String credential) {
        try {
            GoogleIdToken token = verifier.verify(credential);

            if (token == null) {
                throw new InvalidGoogleTokenException();
            }

            GoogleIdToken.Payload payload = token.getPayload();

            return new GoogleAuthorizationResult(payload.getEmail());
        } catch (GeneralSecurityException | IOException exception) {
            throw new GoogleAuthenticationException(exception);
        }
    }
}
