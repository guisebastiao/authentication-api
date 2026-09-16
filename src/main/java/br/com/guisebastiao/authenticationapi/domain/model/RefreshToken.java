package br.com.guisebastiao.authenticationapi.domain.model;

import java.time.Instant;
import java.util.UUID;

public class RefreshToken {
    private UUID id;
    private Session session;
    private RefreshToken revokedBy;
    private String tokenHash;
    private Instant usedAt;
    private Instant createdAt;
    private Instant updatedAt;

    public RefreshToken() {}

    public RefreshToken(
            UUID id,
            Session session,
            RefreshToken revokedBy,
            String tokenHash,
            Instant usedAt,
            Instant createdAt,
            Instant updatedAt
    ) {
        this.id = id;
        this.session = session;
        this.revokedBy = revokedBy;
        this.tokenHash = tokenHash;
        this.usedAt = usedAt;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public Session getSession() {
        return session;
    }

    public void setSession(Session session) {
        this.session = session;
    }

    public RefreshToken getRevokedBy() {
        return revokedBy;
    }

    public void setRevokedBy(RefreshToken revokedBy) {
        this.revokedBy = revokedBy;
    }

    public String getTokenHash() {
        return tokenHash;
    }

    public void setTokenHash(String tokenHash) {
        this.tokenHash = tokenHash;
    }

    public Instant getUsedAt() {
        return usedAt;
    }

    public void setUsedAt(Instant usedAt) {
        this.usedAt = usedAt;
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
