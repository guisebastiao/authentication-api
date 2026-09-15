package br.com.guisebastiao.authenticationapi.domain.model;

import br.com.guisebastiao.authenticationapi.domain.enums.DeviceType;
import br.com.guisebastiao.authenticationapi.domain.valueobject.*;

public class Device {
    private Id id;
    private IdentifierHash identifierHash;
    private DeviceName name;
    private DeviceType type;
    private OS os;
    private UserAgent userAgent;
    private CreatedAt createdAt;
    private UpdatedAt updatedAt;

    public Device() {}

    public Device(Id id, IdentifierHash identifierHash, DeviceName name, DeviceType type, OS os, UserAgent userAgent, CreatedAt createdAt, UpdatedAt updatedAt) {
        this.id = id;
        this.identifierHash = identifierHash;
        this.name = name;
        this.type = type;
        this.os = os;
        this.userAgent = userAgent;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public Id getId() {
        return id;
    }

    public void setId(Id id) {
        this.id = id;
    }

    public IdentifierHash getIdentifierHash() {
        return identifierHash;
    }

    public void setIdentifierHash(IdentifierHash identifierHash) {
        this.identifierHash = identifierHash;
    }

    public DeviceName getName() {
        return name;
    }

    public void setName(DeviceName name) {
        this.name = name;
    }

    public DeviceType getType() {
        return type;
    }

    public void setType(DeviceType type) {
        this.type = type;
    }

    public OS getOs() {
        return os;
    }

    public void setOs(OS os) {
        this.os = os;
    }

    public UserAgent getUserAgent() {
        return userAgent;
    }

    public void setUserAgent(UserAgent userAgent) {
        this.userAgent = userAgent;
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
