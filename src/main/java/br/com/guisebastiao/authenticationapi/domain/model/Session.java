package br.com.guisebastiao.authenticationapi.domain.model;

import br.com.guisebastiao.authenticationapi.domain.valueobject.*;

public class Session {
    private Id id;
    private AccountDevice accountDevice;
    private IdentifierHash identifierHash;
    private RevokedAt revokedAt;
    private CreatedAt createdAt;
    private UpdatedAt updatedAt;

    public Session() {}

    public Session(Id id, AccountDevice accountDevice, IdentifierHash identifierHash, RevokedAt revokedAt, CreatedAt createdAt, UpdatedAt updatedAt) {
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
