package br.com.guisebastiao.authenticationapi.domain.model;

import java.time.Instant;

public class AccountDevice {
    private Account account;
    private Device device;
    private String ipAddress;
    private Instant lastSeenAt;

    public AccountDevice() {}

    public AccountDevice(
            Account account,
            Device device,
            String ipAddress,
            Instant lastSeenAt
    ) {
        this.account = account;
        this.device = device;
        this.ipAddress = ipAddress;
        this.lastSeenAt = lastSeenAt;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public Device getDevice() {
        return device;
    }

    public void setDevice(Device device) {
        this.device = device;
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    public Instant getLastSeenAt() {
        return lastSeenAt;
    }

    public void setLastSeenAt(Instant lastSeenAt) {
        this.lastSeenAt = lastSeenAt;
    }
}
