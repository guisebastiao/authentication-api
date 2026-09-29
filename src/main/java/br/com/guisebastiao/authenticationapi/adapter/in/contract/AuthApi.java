package br.com.guisebastiao.authenticationapi.adapter.in.contract;

import br.com.guisebastiao.authenticationapi.adapter.in.dto.common.ApiBody;
import br.com.guisebastiao.authenticationapi.adapter.in.dto.request.*;
import br.com.guisebastiao.authenticationapi.adapter.in.dto.response.AccountActivationResponse;
import br.com.guisebastiao.authenticationapi.adapter.in.dto.response.AuthResponse;
import br.com.guisebastiao.authenticationapi.adapter.out.security.SecurityAccount;
import jakarta.servlet.http.HttpServletRequest;

public interface AuthApi {

    ApiBody<AuthResponse> signIn(
            HttpServletRequest request,
            SignInRequest dto
    );

    ApiBody<AuthResponse> googleSignIn(
            HttpServletRequest request,
            GoogleSignInRequest dto
    );

    ApiBody<AccountActivationResponse> signUp(
            SignUpRequest dto
    );

    ApiBody<Void> signOut(
            HttpServletRequest request,
            SecurityAccount securityAccount
    );

    ApiBody<AuthResponse> refresh(
            HttpServletRequest request,
            RefreshTokenRequest dto
    );
}
