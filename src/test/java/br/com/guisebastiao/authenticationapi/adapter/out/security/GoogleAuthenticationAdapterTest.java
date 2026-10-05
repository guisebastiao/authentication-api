package br.com.guisebastiao.authenticationapi.adapter.out.security;

import br.com.guisebastiao.authenticationapi.application.result.GoogleAuthorizationResult;
import br.com.guisebastiao.authenticationapi.domain.exception.GoogleAuthenticationException;
import br.com.guisebastiao.authenticationapi.domain.exception.InvalidGoogleTokenException;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.IOException;
import java.security.GeneralSecurityException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.mock;

@ExtendWith(MockitoExtension.class)
class GoogleAuthenticationAdapterTest {

    @Mock
    private GoogleIdTokenVerifier verifier;

    @InjectMocks
    private GoogleAuthenticationAdapter adapter;

    @Test
    @DisplayName("Should return the email from the verified Google token")
    void givenValidCredential_whenAuthorize_thenReturnGoogleEmail() throws Exception {
        String credential = "google-credential";
        GoogleIdToken token = mock(GoogleIdToken.class);
        GoogleIdToken.Payload payload = new GoogleIdToken.Payload()
                .setEmail("user@example.com");

        given(verifier.verify(credential)).willReturn(token);
        given(token.getPayload()).willReturn(payload);

        GoogleAuthorizationResult result = adapter.authorize(credential);

        assertEquals("user@example.com", result.email());
        then(verifier).should().verify(credential);
    }

    @Test
    @DisplayName("Should reject an unverifiable Google token")
    void givenNoVerifiedToken_whenAuthorize_thenThrowInvalidGoogleTokenException() throws Exception {
        String credential = "invalid-credential";
        given(verifier.verify(credential)).willReturn(null);

        assertThrows(
                InvalidGoogleTokenException.class,
                () -> adapter.authorize(credential)
        );
    }

    @Test
    @DisplayName("Should translate Google security failures")
    void givenSecurityFailure_whenAuthorize_thenThrowGoogleAuthenticationException() throws Exception {
        String credential = "google-credential";
        GeneralSecurityException cause = new GeneralSecurityException("Verification failed");
        org.mockito.BDDMockito.willThrow(cause)
                .given(verifier)
                .verify(credential);

        GoogleAuthenticationException exception = assertThrows(
                GoogleAuthenticationException.class,
                () -> adapter.authorize(credential)
        );

        assertSame(cause, exception.getCause());
    }

    @Test
    @DisplayName("Should translate Google I/O failures")
    void givenIoFailure_whenAuthorize_thenThrowGoogleAuthenticationException() throws Exception {
        String credential = "google-credential";
        IOException cause = new IOException("Google service unavailable");
        org.mockito.BDDMockito.willThrow(cause)
                .given(verifier)
                .verify(credential);

        GoogleAuthenticationException exception = assertThrows(
                GoogleAuthenticationException.class,
                () -> adapter.authorize(credential)
        );

        assertSame(cause, exception.getCause());
    }
}
