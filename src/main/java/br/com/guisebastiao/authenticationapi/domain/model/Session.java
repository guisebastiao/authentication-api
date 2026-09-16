package br.com.guisebastiao.authenticationapi.domain.model;

import java.time.Instant;
import java.util.UUID;

public class Session {
    private UUID id;
    private AccountDevice accountDevice;
    private String identifierHash;
    private Instant revokedAt;
    private Instant createdAt;
    private Instant updatedAt;

    public Session() {}

    public Session(
            UUID id,
            AccountDevice accountDevice,
            String identifierHash,
            Instant revokedAt,
            Instant createdAt,
            Instant updatedAt
    ) {
        this.id = id;
        this.accountDevice = accountDevice;
        this.identifierHash = identifierHash;
        this.revokedAt = revokedAt;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public AccountDevice getAccountDevice() {
        return accountDevice;
    }

    public void setAccountDevice(AccountDevice accountDevice) {
        this.accountDevice = accountDevice;
    }

    public String getIdentifierHash() {
        return identifierHash;
    }

    public void setIdentifierHash(String identifierHash) {
        this.identifierHash = identifierHash;
    }

    public Instant getRevokedAt() {
        return revokedAt;
    }

    public void setRevokedAt(Instant revokedAt) {
        this.revokedAt = revokedAt;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Instant createdAt) {
        this.createdAt = createdAt;
    }

    public Instant getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Instant updatedAt) {
        this.updatedAt = updatedAt;
    }
}
