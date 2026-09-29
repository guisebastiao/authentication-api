package br.com.guisebastiao.authenticationapi.adapter.in.contract;

import br.com.guisebastiao.authenticationapi.adapter.in.dto.common.ApiBody;
import br.com.guisebastiao.authenticationapi.adapter.in.dto.request.AccountActivateRequest;
import br.com.guisebastiao.authenticationapi.adapter.in.dto.request.ResendAccountActivationEmailRequest;
import br.com.guisebastiao.authenticationapi.adapter.in.dto.response.AccountActivationResponse;
import jakarta.servlet.http.HttpServletRequest;

public interface AccountActivationApi {
    ApiBody<Void> activate(
            HttpServletRequest request,
            AccountActivateRequest dto
    );

    ApiBody<AccountActivationResponse> resend(
            HttpServletRequest request,
            ResendAccountActivationEmailRequest dto
    );
}
