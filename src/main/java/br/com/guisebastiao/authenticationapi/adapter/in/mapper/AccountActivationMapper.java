package br.com.guisebastiao.authenticationapi.adapter.in.mapper;

import br.com.guisebastiao.authenticationapi.adapter.in.dto.request.AccountActivateRequest;
import br.com.guisebastiao.authenticationapi.adapter.in.dto.response.AccountActivationResponse;
import br.com.guisebastiao.authenticationapi.application.command.AccountActivationCommand;
import br.com.guisebastiao.authenticationapi.application.result.AccountActivationResult;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AccountActivationMapper {
    AccountActivationCommand toCommand(AccountActivateRequest request);

    AccountActivationResponse toResponse(AccountActivationResult result);
}
