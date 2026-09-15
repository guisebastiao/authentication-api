package br.com.guisebastiao.authenticationapi.domain.model;

import br.com.guisebastiao.authenticationapi.domain.valueobject.*;

public class RecoverPassword {
    private Id id;
    private Account account;
    private TokenHash tokenHash;
    private ExpiresAt expiresAt;
    private UsedAt usedAt;
    private ResendAvailableAt resendAvailableAt;
    private CreatedAt createdAt;
    private UpdatedAt updatedAt;

    public RecoverPassword() {}

    public RecoverPassword(Id id, Account account, TokenHash tokenHash, ExpiresAt expiresAt, UsedAt usedAt, ResendAvailableAt resendAvailableAt, CreatedAt createdAt, UpdatedAt updatedAt) {
        this.id = id;
        this.account = account;
        this.tokenHash = tokenHash;
        this.expiresAt = expiresAt;
        this.usedAt = usedAt;
        this.resendAvailableAt = resendAvailableAt;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public Id getId() {
        return id;
    }

    public void setId(Id id) {
        this.id = id;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public TokenHash getTokenHash() {
        return tokenHash;
    }

    public void setTokenHash(TokenHash tokenHash) {
        this.tokenHash = tokenHash;
    }

    public ExpiresAt getExpiresAt() {
        return expiresAt;
    }

    public void setExpiresAt(ExpiresAt expiresAt) {
        this.expiresAt = expiresAt;
    }

    public UsedAt getUsedAt() {
        return usedAt;
    }

    public void setUsedAt(UsedAt usedAt) {
        this.usedAt = usedAt;
    }

    public ResendAvailableAt getResendAvailableAt() {
        return resendAvailableAt;
    }

    public void setResendAvailableAt(ResendAvailableAt resendAvailableAt) {
        this.resendAvailableAt = resendAvailableAt;
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
