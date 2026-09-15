package br.com.guisebastiao.authenticationapi.domain.model;

import br.com.guisebastiao.authenticationapi.domain.enums.AccountStatus;
import br.com.guisebastiao.authenticationapi.domain.valueobject.*;

import java.util.Set;

public class Account {
    private Id id;
    private Set<AccountRole> roles;
    private Email email;
    private PasswordHash passwordHash;
    private AccountStatus status;
    private DisabledAt disabledAt;
    private Timestamp createdAt;
    private Timestamp updatedAt;

    public Account() {}

    public Account(Id id, Set<AccountRole> roles, Email email, PasswordHash passwordHash, AccountStatus status, DisabledAt disabledAt, Timestamp createdAt, Timestamp updatedAt) {
        this.id = id;
        this.roles = roles;
        this.email = email;
        this.passwordHash = passwordHash;
        this.status = status;
        this.disabledAt = disabledAt;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public Id getId() {
        return id;
    }

    public void setId(Id id) {
        this.id = id;
    }

    public Set<AccountRole> getRoles() {
        return roles;
    }

    public void setRoles(Set<AccountRole> roles) {
        this.roles = roles;
    }

    public Email getEmail() {
        return email;
    }

    public void setEmail(Email email) {
        this.email = email;
    }

    public PasswordHash getPasswordHash() {
        return passwordHash;
    }

    public void setPasswordHash(PasswordHash passwordHash) {
        this.passwordHash = passwordHash;
    }

    public AccountStatus getStatus() {
        return status;
    }

    public void setStatus(AccountStatus status) {
        this.status = status;
    }

    public DisabledAt getDisabledAt() {
        return disabledAt;
    }

    public void setDisabledAt(DisabledAt disabledAt) {
        this.disabledAt = disabledAt;
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
