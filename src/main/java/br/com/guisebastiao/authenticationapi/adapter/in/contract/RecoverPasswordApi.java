package br.com.guisebastiao.authenticationapi.adapter.in.contract;

import br.com.guisebastiao.authenticationapi.adapter.in.dto.common.ApiBody;
import br.com.guisebastiao.authenticationapi.adapter.in.dto.request.CreateRecoverPasswordRequest;
import br.com.guisebastiao.authenticationapi.adapter.in.dto.request.ResendRecoverPasswordEmailRequest;
import br.com.guisebastiao.authenticationapi.adapter.in.dto.request.ResetPasswordRequest;
import br.com.guisebastiao.authenticationapi.adapter.in.dto.request.ValidateRecoverPasswordRequest;
import br.com.guisebastiao.authenticationapi.adapter.in.dto.response.RecoverPasswordResponse;
import jakarta.servlet.http.HttpServletRequest;

public interface RecoverPasswordApi {
    ApiBody<RecoverPasswordResponse> create(
            HttpServletRequest request,
            CreateRecoverPasswordRequest dto
    );

    ApiBody<RecoverPasswordResponse> validate(
            HttpServletRequest request,
            ValidateRecoverPasswordRequest dto
    );

    ApiBody<RecoverPasswordResponse> resend(
            HttpServletRequest request,
            ResendRecoverPasswordEmailRequest dto
    );

    ApiBody<Void> reset(
            ResetPasswordRequest dto
    );
}
