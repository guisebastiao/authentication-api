package br.com.guisebastiao.authenticationapi.domain.model;

import br.com.guisebastiao.authenticationapi.domain.valueobject.*;

public class AccountActivation {
    private Id id;
    private Account account;
    private TokenHash tokenHash;
    private OptHash otpHash;
    private ExpiresAt expiresAt;
    private ResendAvailableAt resendAvailableAt;
    private Timestamp createdAt;
    private Timestamp updatedAt;

    public AccountActivation() {}

    public AccountActivation(Id id, Account account, TokenHash tokenHash, OptHash otpHash, ExpiresAt expiresAt, ResendAvailableAt resendAvailableAt, Timestamp createdAt, Timestamp updatedAt) {
        this.id = id;
        this.account = account;
        this.tokenHash = tokenHash;
        this.otpHash = otpHash;
        this.expiresAt = expiresAt;
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

    public OptHash getOtpHash() {
        return otpHash;
    }

    public void setOptHash(OptHash optHash) {
        this.otpHash = optHash;
    }

    public ExpiresAt getExpiresAt() {
        return expiresAt;
    }

    public void setExpiresAt(ExpiresAt expiresAt) {
        this.expiresAt = expiresAt;
    }

    public ResendAvailableAt getResendAvailableAt() {
        return resendAvailableAt;
    }

    public void setResendAvailableAt(ResendAvailableAt resendAvailableAt) {
        this.resendAvailableAt = resendAvailableAt;
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
