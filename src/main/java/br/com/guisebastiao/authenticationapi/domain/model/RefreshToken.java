package br.com.guisebastiao.authenticationapi.domain.model;

import br.com.guisebastiao.authenticationapi.domain.valueobject.*;

public class RefreshToken {
    private Id id;
    private Session session;
    private IdentifierHash identifierHash;
    private ExpiresAt expiresAt;
    private RevokedAt revokedAt;
    private CreatedAt createdAt;
    private UpdatedAt updatedAt;

    public RefreshToken() {}

    public RefreshToken(Id id, Session session, IdentifierHash identifierHash, ExpiresAt expiresAt, RevokedAt revokedAt, CreatedAt createdAt, UpdatedAt updatedAt) {
        this.id = id;
        this.session = session;
        this.identifierHash = identifierHash;
        this.expiresAt = expiresAt;
        this.revokedAt = revokedAt;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public Id getId() {
        return id;
    }

    public void setId(Id id) {
        this.id = id;
    }

    public Session getSession() {
        return session;
    }

    public void setSession(Session session) {
        this.session = session;
    }

    public IdentifierHash getIdentifierHash() {
        return identifierHash;
    }

    public void setIdentifierHash(IdentifierHash identifierHash) {
        this.identifierHash = identifierHash;
    }

    public ExpiresAt getExpiresAt() {
        return expiresAt;
    }

    public void setExpiresAt(ExpiresAt expiresAt) {
        this.expiresAt = expiresAt;
    }

    public RevokedAt getRevokedAt() {
        return revokedAt;
    }

    public void setRevokedAt(RevokedAt revokedAt) {
        this.revokedAt = revokedAt;
    }

    public CreatedAt getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(CreatedAt createdAt) {
        this.createdAt = createdAt;
    }

    public UpdatedAt getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(UpdatedAt updatedAt) {
        this.updatedAt = updatedAt;
    }
}
