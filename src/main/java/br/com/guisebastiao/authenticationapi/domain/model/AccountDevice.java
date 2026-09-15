package br.com.guisebastiao.authenticationapi.domain.model;

import br.com.guisebastiao.authenticationapi.domain.valueobject.IpAddress;
import br.com.guisebastiao.authenticationapi.domain.valueobject.LastSeenAt;

public class AccountDevice {
    private Account account;
    private Device device;
    private IpAddress ipAddress;
    private LastSeenAt lastSeenAt;

    public AccountDevice() {}

    public AccountDevice(Account account, Device device, IpAddress ipAddress, LastSeenAt lastSeenAt) {
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

    public IpAddress getIpAddress() {
        return ipAddress;
    }

    public void setIpAddress(IpAddress ipAddress) {
        this.ipAddress = ipAddress;
    }

    public LastSeenAt getLastSeenAt() {
        return lastSeenAt;
    }

    public void setLastSeenAt(LastSeenAt lastSeenAt) {
        this.lastSeenAt = lastSeenAt;
    }
}
