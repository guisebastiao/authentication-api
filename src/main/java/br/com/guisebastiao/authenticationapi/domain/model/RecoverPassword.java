package br.com.guisebastiao.authenticationapi.domain.model;

import java.time.Instant;
import java.util.UUID;

public class RecoverPassword {
    private UUID id;
    private Account account;
    private String recoverToken;
    private String otpCodeHash;
    private Instant expiresAt;
    private Instant usedAt;
    private Instant verifiedAt;
    private Instant resendAvailableAt;
    private Instant createdAt;
    private Instant updatedAt;

    public RecoverPassword() {}

    public RecoverPassword(
            UUID id,
            Account account,
            String recoverToken,
            String otpCodeHash,
            Instant expiresAt,
            Instant usedAt,
            Instant verifiedAt,
            Instant resendAvailableAt,
            Instant createdAt,
            Instant updatedAt
    ) {
        this.id = id;
        this.account = account;
        this.recoverToken = recoverToken;
        this.otpCodeHash = otpCodeHash;
        this.expiresAt = expiresAt;
        this.usedAt = usedAt;
        this.verifiedAt = verifiedAt;
        this.resendAvailableAt = resendAvailableAt;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public String getRecoverToken() {
        return recoverToken;
    }

    public void setRecoverToken(String recoverToken) {
        this.recoverToken = recoverToken;
    }

    public String getOtpCodeHash() {
        return otpCodeHash;
    }

    public void setOtpCodeHash(String otpCodeHash) {
        this.otpCodeHash = otpCodeHash;
    }

    public Instant getExpiresAt() {
        return expiresAt;
    }

    public void setExpiresAt(Instant expiresAt) {
        this.expiresAt = expiresAt;
    }

    public Instant getUsedAt() {
        return usedAt;
    }

    public void setUsedAt(Instant usedAt) {
        this.usedAt = usedAt;
    }

    public Instant getVerifiedAt() {
        return verifiedAt;
    }

    public void setVerifiedAt(Instant verifiedAt) {
        this.verifiedAt = verifiedAt;
    }

    public Instant getResendAvailableAt() {
        return resendAvailableAt;
    }

    public void setResendAvailableAt(Instant resendAvailableAt) {
        this.resendAvailableAt = resendAvailableAt;
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
