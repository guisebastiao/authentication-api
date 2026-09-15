package br.com.guisebastiao.authenticationapi.domain.model;

public class AccountRole {
    private Account account;
    private Role role;

    public AccountRole() {}

    public AccountRole(Account account, Role role) {
        this.account = account;
        this.role = role;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}
