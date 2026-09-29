package br.com.guisebastiao.authenticationapi.adapter.out.persistence.mappers;

import br.com.guisebastiao.authenticationapi.adapter.out.persistence.entity.AccountActivationEntity;
import br.com.guisebastiao.authenticationapi.domain.model.AccountActivation;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface JpaAccountActivationMapper {
    AccountActivationEntity toEntity(AccountActivation domain);

    AccountActivation toDomain(AccountActivationEntity entity);
}
