package br.com.guisebastiao.authenticationapi.adapter.out.persistence.mappers;

import br.com.guisebastiao.authenticationapi.adapter.out.persistence.entity.RefreshEntity;
import br.com.guisebastiao.authenticationapi.domain.model.Refresh;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface JpaRefreshMapper {
    RefreshEntity toEntity(Refresh domain);

    Refresh toDomain(RefreshEntity entity);
}
