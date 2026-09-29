package br.com.guisebastiao.authenticationapi.adapter.out.persistence.mappers;

import br.com.guisebastiao.authenticationapi.adapter.out.persistence.entity.RoleEntity;
import br.com.guisebastiao.authenticationapi.domain.model.Role;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface JpaRoleMapper {
    RoleEntity toEntity(Role domain);

    Role toDomain(RoleEntity entity);
}
