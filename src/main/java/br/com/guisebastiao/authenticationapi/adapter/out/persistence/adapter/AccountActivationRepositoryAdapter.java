package br.com.guisebastiao.authenticationapi.adapter.out.persistence.adapter;

import br.com.guisebastiao.authenticationapi.adapter.out.persistence.entity.AccountActivationEntity;
import br.com.guisebastiao.authenticationapi.adapter.out.persistence.mappers.JpaAccountActivationMapper;
import br.com.guisebastiao.authenticationapi.adapter.out.persistence.repository.JpaAccountActivationRepository;
import br.com.guisebastiao.authenticationapi.application.port.out.AccountActivationRepositoryPort;
import br.com.guisebastiao.authenticationapi.domain.model.AccountActivation;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class AccountActivationRepositoryAdapter implements AccountActivationRepositoryPort {
    private final JpaAccountActivationRepository repository;
    private final JpaAccountActivationMapper mapper;

    @Override
    public void save(List<AccountActivation> accountActivation) {
        List<AccountActivationEntity> entities = accountActivation.stream()
                .map(mapper::toEntity)
                .toList();

        repository.saveAll(entities);
    }

    @Override
    public AccountActivation save(AccountActivation accountActivation) {
        AccountActivationEntity entity = mapper.toEntity(accountActivation);
        AccountActivationEntity saved = repository.save(entity);
        return mapper.toDomain(saved);
    }

    @Override
    public Optional<AccountActivation> findByActivationTokenHash(String activationTokenHash) {
        return repository.findByActivationTokenHash(activationTokenHash).map(mapper::toDomain);
    }

    @Override
    public int deleteAllExpired(Instant now) {
        return repository.deleteAllExpired(now);
    }
}
