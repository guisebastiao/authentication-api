package br.com.guisebastiao.authenticationapi.adapter.out.persistence.mappers;

import br.com.guisebastiao.authenticationapi.adapter.out.persistence.entity.SessionEntity;
import br.com.guisebastiao.authenticationapi.domain.model.Session;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface JpaSessionMapper {
    SessionEntity toEntity(Session domain);

    Session toDomain(SessionEntity entity);
}
