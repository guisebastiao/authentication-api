package br.com.guisebastiao.authenticationapi.adapter.out.persistence.mappers;

import br.com.guisebastiao.authenticationapi.adapter.out.persistence.entity.AccountEntity;
import br.com.guisebastiao.authenticationapi.domain.model.Account;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface JpaAccountMapper {
    AccountEntity toEntity(Account domain);

    Account toDomain(AccountEntity entity);
}
