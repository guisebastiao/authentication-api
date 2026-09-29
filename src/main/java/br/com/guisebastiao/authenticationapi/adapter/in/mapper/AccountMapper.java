package br.com.guisebastiao.authenticationapi.adapter.in.mapper;

import br.com.guisebastiao.authenticationapi.adapter.in.dto.request.ChangePasswordRequest;
import br.com.guisebastiao.authenticationapi.adapter.in.dto.request.DisableAccountRequest;
import br.com.guisebastiao.authenticationapi.adapter.in.dto.response.AccountResponse;
import br.com.guisebastiao.authenticationapi.application.command.ChangePasswordCommand;
import br.com.guisebastiao.authenticationapi.application.command.DisableAccountCommand;
import br.com.guisebastiao.authenticationapi.application.result.AccountResult;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AccountMapper {
    AccountResponse toResponse(AccountResult result);

    ChangePasswordCommand toCommand(ChangePasswordRequest request);

    DisableAccountCommand toCommand(DisableAccountRequest request);
}
