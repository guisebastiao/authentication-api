package br.com.guisebastiao.authenticationapi.adapter.in.contract;

import br.com.guisebastiao.authenticationapi.adapter.in.dto.common.ApiBody;
import br.com.guisebastiao.authenticationapi.adapter.in.dto.request.ChangePasswordRequest;
import br.com.guisebastiao.authenticationapi.adapter.in.dto.request.DisableAccountRequest;
import br.com.guisebastiao.authenticationapi.adapter.in.dto.response.AccountResponse;
import br.com.guisebastiao.authenticationapi.adapter.out.security.SecurityAccount;

public interface AccountApi {

    ApiBody<AccountResponse> me(
            SecurityAccount securityAccount
    );

    ApiBody<Void> changePassword(
            SecurityAccount securityAccount,
            ChangePasswordRequest dto
    );

    ApiBody<Void> disableAccount(
            SecurityAccount securityAccount,
            DisableAccountRequest dto
    );
}
