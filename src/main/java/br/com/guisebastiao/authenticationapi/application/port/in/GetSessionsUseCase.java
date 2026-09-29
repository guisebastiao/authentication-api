package br.com.guisebastiao.authenticationapi.application.port.in;

import br.com.guisebastiao.authenticationapi.application.result.PageResult;
import br.com.guisebastiao.authenticationapi.application.command.PageQueryCommand;
import br.com.guisebastiao.authenticationapi.application.result.SessionResult;
import br.com.guisebastiao.authenticationapi.domain.model.Account;

public interface GetSessionsUseCase {
    PageResult<SessionResult> execute(Account account, String currentSession, PageQueryCommand paging);
}
