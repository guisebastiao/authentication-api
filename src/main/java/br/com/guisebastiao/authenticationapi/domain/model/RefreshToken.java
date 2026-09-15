package br.com.guisebastiao.authenticationapi.domain.model;

import br.com.guisebastiao.authenticationapi.domain.valueobject.*;

public class RefreshToken {
    private Id id;
    private Session session;
    private IdentifierHash identifierHash;
    private ExpiresAt expiresAt;
    private RevokedAt revokedAt;
    private Timestamp createdAt;
    private Timestamp updatedAt;

    public RefreshToken() {}

    public RefreshToken(Id id, Session session, IdentifierHash identifierHash, ExpiresAt expiresAt, RevokedAt revokedAt, Timestamp createdAt, Timestamp updatedAt) {
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

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    public Timestamp getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Timestamp updatedAt) {
        this.updatedAt = updatedAt;
    }
}
