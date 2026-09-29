package br.com.guisebastiao.authenticationapi.adapter.in.mapper;

import br.com.guisebastiao.authenticationapi.adapter.in.dto.request.PaginationRequest;
import br.com.guisebastiao.authenticationapi.adapter.in.dto.response.SessionResponse;
import br.com.guisebastiao.authenticationapi.application.command.PageQueryCommand;
import br.com.guisebastiao.authenticationapi.application.result.SessionResult;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface SessionMapper {
    SessionResponse toResponse(SessionResult result);

    PageQueryCommand toPaging(PaginationRequest request);
}
