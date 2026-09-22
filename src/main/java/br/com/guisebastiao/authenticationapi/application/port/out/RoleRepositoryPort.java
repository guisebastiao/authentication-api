package br.com.guisebastiao.authenticationapi.application.port.out;

import br.com.guisebastiao.authenticationapi.domain.model.Role;

import java.util.Optional;

public interface RoleRepositoryPort {
    Optional<Role> findRoleByName(String name);
}
