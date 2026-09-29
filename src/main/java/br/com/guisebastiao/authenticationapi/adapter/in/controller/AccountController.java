package br.com.guisebastiao.authenticationapi.adapter.in.controller;

import br.com.guisebastiao.authenticationapi.adapter.in.contract.AccountApi;
import br.com.guisebastiao.authenticationapi.adapter.in.dto.common.ApiBody;
import br.com.guisebastiao.authenticationapi.adapter.in.dto.request.ChangePasswordRequest;
import br.com.guisebastiao.authenticationapi.adapter.in.dto.request.DisableAccountRequest;
import br.com.guisebastiao.authenticationapi.adapter.in.dto.response.AccountResponse;
import br.com.guisebastiao.authenticationapi.adapter.in.mapper.AccountMapper;
import br.com.guisebastiao.authenticationapi.adapter.out.security.SecurityAccount;
import br.com.guisebastiao.authenticationapi.application.command.ChangePasswordCommand;
import br.com.guisebastiao.authenticationapi.application.command.DisableAccountCommand;
import br.com.guisebastiao.authenticationapi.application.port.in.ChangePasswordUseCase;
import br.com.guisebastiao.authenticationapi.application.port.in.DisableAccountUseCase;
import br.com.guisebastiao.authenticationapi.application.port.in.GetCurrentAccountUseCase;
import br.com.guisebastiao.authenticationapi.application.result.AccountResult;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/accounts")
@RequiredArgsConstructor
public class AccountController implements AccountApi {
    private final GetCurrentAccountUseCase getCurrentAccountUseCase;
    private final ChangePasswordUseCase changePasswordUseCase;
    private final DisableAccountUseCase disableAccountUseCase;
    private final AccountMapper mapper;

    @Override
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/me")
    public ApiBody<AccountResponse> me(
            @AuthenticationPrincipal SecurityAccount securityAccount
    ) {
        AccountResult data = getCurrentAccountUseCase.execute(securityAccount.account());

        return ApiBody.of(mapper.toResponse(data));
    }

    @Override
    @ResponseStatus(HttpStatus.OK)
    @PatchMapping("/me/password")
    public ApiBody<Void> changePassword(
            @AuthenticationPrincipal SecurityAccount securityAccount,
            @RequestBody @Valid ChangePasswordRequest dto
    ) {
        ChangePasswordCommand command = mapper.toCommand(dto);

        changePasswordUseCase.execute(securityAccount.account(), command);

        return ApiBody.of();
    }

    @Override
    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping("/me")
    public ApiBody<Void> disableAccount(
            @AuthenticationPrincipal SecurityAccount securityAccount,
            @RequestBody @Valid DisableAccountRequest dto
    ) {
        DisableAccountCommand command = mapper.toCommand(dto);

        disableAccountUseCase.execute(securityAccount.account(), command);

        return ApiBody.of();
    }
}
