package br.com.guisebastiao.authenticationapi.adapter.in.mapper;

import br.com.guisebastiao.authenticationapi.adapter.in.dto.request.CreateRecoverPasswordRequest;
import br.com.guisebastiao.authenticationapi.adapter.in.dto.request.ResetPasswordRequest;
import br.com.guisebastiao.authenticationapi.adapter.in.dto.request.ValidateRecoverPasswordRequest;
import br.com.guisebastiao.authenticationapi.adapter.in.dto.response.RecoverPasswordResponse;
import br.com.guisebastiao.authenticationapi.application.command.CreateRecoverPasswordCommand;
import br.com.guisebastiao.authenticationapi.application.command.ResetPasswordCommand;
import br.com.guisebastiao.authenticationapi.application.command.ValidateRecoverPasswordCommand;
import br.com.guisebastiao.authenticationapi.application.result.RecoverPasswordResult;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface RecoverPasswordMapper {
    CreateRecoverPasswordCommand toCommand(CreateRecoverPasswordRequest request);

    ResetPasswordCommand toCommand(ResetPasswordRequest request);

    ValidateRecoverPasswordCommand toCommand(ValidateRecoverPasswordRequest request);

    RecoverPasswordResponse toResponse(RecoverPasswordResult result);
}
