package br.com.guisebastiao.authenticationapi.adapter.in.mapper;

import br.com.guisebastiao.authenticationapi.adapter.in.dto.request.*;
import br.com.guisebastiao.authenticationapi.adapter.in.dto.response.AccountActivationResponse;
import br.com.guisebastiao.authenticationapi.adapter.in.dto.response.AuthResponse;
import br.com.guisebastiao.authenticationapi.application.command.*;
import br.com.guisebastiao.authenticationapi.application.result.AccountActivationResult;
import br.com.guisebastiao.authenticationapi.application.result.AuthResult;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AuthMapper {
    AuthResponse toResponse(AuthResult result);

    AccountActivationResponse toResponse(AccountActivationResult result);

    SignInCommand toCommand(SignInRequest request);

    CreateAccountCommand toCommand(SignUpRequest request);

    GoogleSignInCommand toCommand(GoogleSignInRequest request);

    RefreshTokenCommand toCommand(RefreshTokenRequest request);
}
