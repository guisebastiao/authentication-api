package br.com.guisebastiao.authenticationapi.adapter.out.persistence.repository;

import br.com.guisebastiao.authenticationapi.adapter.out.persistence.entity.AccountActivationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface JpaAccountActivationRepository extends JpaRepository<AccountActivationEntity, UUID> {
    Optional<AccountActivationEntity> findByActivationTokenHash(String activationToken);

    @Query("""
        SELECT aa FROM AccountActivationEntity aa
        WHERE aa.account.id = :accountId
            AND aa.expiresAt IS NULL
    """)
    List<AccountActivationEntity> findAllByAccountIdAndNotExpired(@Param("accountId") UUID accountId);
}
