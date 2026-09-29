package br.com.guisebastiao.authenticationapi.adapter.in.controller;

import br.com.guisebastiao.authenticationapi.adapter.in.contract.AuthApi;
import br.com.guisebastiao.authenticationapi.adapter.in.dto.common.ApiBody;
import br.com.guisebastiao.authenticationapi.adapter.in.dto.request.*;
import br.com.guisebastiao.authenticationapi.adapter.in.dto.response.AccountActivationResponse;
import br.com.guisebastiao.authenticationapi.adapter.in.dto.response.AuthResponse;
import br.com.guisebastiao.authenticationapi.adapter.in.mapper.AuthMapper;
import br.com.guisebastiao.authenticationapi.adapter.out.security.SecurityAccount;
import br.com.guisebastiao.authenticationapi.application.command.*;
import br.com.guisebastiao.authenticationapi.application.port.in.*;
import br.com.guisebastiao.authenticationapi.application.result.AccountActivationResult;
import br.com.guisebastiao.authenticationapi.application.result.AuthResult;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController implements AuthApi {
    private final CreateAccountUseCase createAccountUseCase;
    private final GoogleSignInUseCase googleSignInUseCase;
    private final RefreshTokenUseCase refreshTokenUseCase;
    private final SignOutUseCase signOutUseCase;
    private final SignInUseCase signInUseCase;
    private final AuthMapper mapper;

    @Override
    @ResponseStatus(HttpStatus.OK)
    @PostMapping("/sign-in")
    public ApiBody<AuthResponse> signIn(
            HttpServletRequest request,
            @RequestBody @Valid SignInRequest dto
    ) {
        SignInCommand command = mapper.toCommand(dto);

        AuthResult data = signInUseCase.execute(
                command,
                request.getHeader("User-Agent"),
                request.getRemoteAddr()
        );

        return ApiBody.of(mapper.toResponse(data));
    }

    @Override
    @ResponseStatus(HttpStatus.OK)
    @PostMapping("/google/sign-in")
    public ApiBody<AuthResponse> googleSignIn(
            HttpServletRequest request,
            @RequestBody @Valid GoogleSignInRequest dto
    ) {
        GoogleSignInCommand command = mapper.toCommand(dto);

        AuthResult data = googleSignInUseCase.execute(
                command,
                request.getHeader("User-Agent"),
                request.getRemoteAddr()
        );

        return ApiBody.of(mapper.toResponse(data));
    }

    @Override
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/sign-up")
    public ApiBody<AccountActivationResponse> signUp(
            @RequestBody @Valid SignUpRequest dto
    ) {
        CreateAccountCommand command = mapper.toCommand(dto);

        AccountActivationResult data = createAccountUseCase.execute(command);

        return ApiBody.of(mapper.toResponse(data));
    }

    @Override
    @ResponseStatus(HttpStatus.OK)
    @PostMapping("/sign-out")
    public ApiBody<Void> signOut(
            HttpServletRequest request,
            @AuthenticationPrincipal SecurityAccount securityAccount
    ) {
        signOutUseCase.execute(
                securityAccount.account(),
                request.getHeader("X-Session")
        );

        return ApiBody.of();
    }

    @Override
    @ResponseStatus(HttpStatus.OK)
    @PostMapping("/refresh")
    public ApiBody<AuthResponse> refresh(
            HttpServletRequest request,
            @RequestBody @Valid RefreshTokenRequest dto
    ) {
        RefreshTokenCommand command = mapper.toCommand(dto);

        AuthResult data = refreshTokenUseCase.execute(command);

        return ApiBody.of(mapper.toResponse(data));
    }
}
