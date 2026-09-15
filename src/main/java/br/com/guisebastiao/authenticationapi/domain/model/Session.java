package br.com.guisebastiao.authenticationapi.domain.model;

import br.com.guisebastiao.authenticationapi.domain.valueobject.*;

public class Session {
    private Id id;
    private AccountDevice accountDevice;
    private IdentifierHash identifierHash;
    private RevokedAt revokedAt;
    private Timestamp createdAt;
    private Timestamp updatedAt;

    public Session() {}

    public Session(Id id, AccountDevice accountDevice, IdentifierHash identifierHash, RevokedAt revokedAt, Timestamp createdAt, Timestamp updatedAt) {
        this.id = id;
        this.accountDevice = accountDevice;
        this.identifierHash = identifierHash;
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

    public AccountDevice getAccountDevice() {
        return accountDevice;
    }

    public void setAccountDevice(AccountDevice accountDevice) {
        this.accountDevice = accountDevice;
    }

    public IdentifierHash getIdentifierHash() {
        return identifierHash;
    }

    public void setIdentifierHash(IdentifierHash identifierHash) {
        this.identifierHash = identifierHash;
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
