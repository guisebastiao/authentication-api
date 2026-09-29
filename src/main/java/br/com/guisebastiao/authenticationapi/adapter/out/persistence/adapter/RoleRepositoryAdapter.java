package br.com.guisebastiao.authenticationapi.adapter.out.persistence.adapter;

import br.com.guisebastiao.authenticationapi.adapter.out.persistence.mappers.JpaRoleMapper;
import br.com.guisebastiao.authenticationapi.adapter.out.persistence.repository.JpaRoleRepository;
import br.com.guisebastiao.authenticationapi.application.port.out.RoleRepositoryPort;
import br.com.guisebastiao.authenticationapi.domain.model.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class RoleRepositoryAdapter implements RoleRepositoryPort {
    private final JpaRoleRepository repository;
    private final JpaRoleMapper mapper;

    @Override
    public Optional<Role> findRoleByName(String name) {
        return repository.findByName(name).map(mapper::toDomain);
    }
}
