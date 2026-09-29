package br.com.guisebastiao.authenticationapi.adapter.out.persistence.adapter;

import br.com.guisebastiao.authenticationapi.adapter.out.persistence.entity.RecoverPasswordEntity;
import br.com.guisebastiao.authenticationapi.adapter.out.persistence.mappers.JpaRecoverPasswordMapper;
import br.com.guisebastiao.authenticationapi.adapter.out.persistence.repository.JpaRecoverPasswordRepository;
import br.com.guisebastiao.authenticationapi.application.port.out.RecoverPasswordRepositoryPort;
import br.com.guisebastiao.authenticationapi.domain.model.RecoverPassword;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class RecoverPasswordRepositoryAdapter implements RecoverPasswordRepositoryPort {
    private final JpaRecoverPasswordRepository repository;
    private final JpaRecoverPasswordMapper mapper;

    @Override
    public RecoverPassword save(RecoverPassword recoverPassword) {
        RecoverPasswordEntity entity = mapper.toEntity(recoverPassword);
        RecoverPasswordEntity saved = repository.save(entity);
        return mapper.toDomain(saved);
    }

    @Override
    public Optional<RecoverPassword> findByRecoverTokenHash(String recoverTokenHash) {
        return repository.findByRecoverTokenHash(recoverTokenHash).map(mapper::toDomain);
    }

    @Override
    public Optional<RecoverPassword> findByRecoverTokenHashAndNotVerified(String recoverTokenHash) {
        return repository.findByRecoverTokenHashAndNotVerified(recoverTokenHash).map(mapper::toDomain);
    }
}
