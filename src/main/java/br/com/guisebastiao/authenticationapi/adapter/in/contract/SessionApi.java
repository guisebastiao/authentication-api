package br.com.guisebastiao.authenticationapi.adapter.in.contract;

import br.com.guisebastiao.authenticationapi.adapter.in.dto.common.ApiBody;
import br.com.guisebastiao.authenticationapi.adapter.in.dto.common.ApiListBody;
import br.com.guisebastiao.authenticationapi.adapter.in.dto.request.PaginationRequest;
import br.com.guisebastiao.authenticationapi.adapter.in.dto.request.RevokeSessionsRequest;
import br.com.guisebastiao.authenticationapi.adapter.in.dto.response.SessionResponse;
import br.com.guisebastiao.authenticationapi.adapter.out.security.SecurityAccount;

public interface SessionApi {
    ApiBody<ApiListBody<SessionResponse>> findAllSessions(
            SecurityAccount securityAccount,
            String sessionToken,
            PaginationRequest pagination
    );

    ApiBody<SessionResponse> findCurrentSession(
            SecurityAccount securityAccount,
            String sessionToken
    );

    ApiBody<Void> revokeSession(
            SecurityAccount securityAccount,
            RevokeSessionsRequest dto
    );

    ApiBody<Void> revokeAllSessions(
            SecurityAccount securityAccount
    );
}
