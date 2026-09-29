package br.com.guisebastiao.authenticationapi.adapter.out.persistence.mappers;

import br.com.guisebastiao.authenticationapi.adapter.out.persistence.entity.RecoverPasswordEntity;
import br.com.guisebastiao.authenticationapi.domain.model.RecoverPassword;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface JpaRecoverPasswordMapper {
    RecoverPasswordEntity toEntity(RecoverPassword domain);

    RecoverPassword toDomain(RecoverPasswordEntity entity);
}
