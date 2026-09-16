package br.com.guisebastiao.authenticationapi.domain.model;

import br.com.guisebastiao.authenticationapi.domain.enums.DeviceType;
import br.com.guisebastiao.authenticationapi.domain.enums.OperatingSystem;

import java.time.Instant;
import java.util.UUID;

public class Device {
    private UUID id;
    private String identifierHash;
    private String name;
    private DeviceType type;
    private OperatingSystem os;
    private String userAgent;
    private Instant createdAt;
    private String updatedAt;

    public Device() {}

    public Device(
            UUID id,
            String identifierHash,
            String name,
            DeviceType type,
            OperatingSystem os,
            String userAgent,
            Instant createdAt,
            String updatedAt
    ) {
        this.id = id;
        this.identifierHash = identifierHash;
        this.name = name;
        this.type = type;
        this.os = os;
        this.userAgent = userAgent;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getIdentifierHash() {
        return identifierHash;
    }

    public void setIdentifierHash(String identifierHash) {
        this.identifierHash = identifierHash;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public DeviceType getType() {
        return type;
    }

    public void setType(DeviceType type) {
        this.type = type;
    }

    public OperatingSystem getOs() {
        return os;
    }

    public void setOs(OperatingSystem os) {
        this.os = os;
    }

    public String getUserAgent() {
        return userAgent;
    }

    public void setUserAgent(String userAgent) {
        this.userAgent = userAgent;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Instant createdAt) {
        this.createdAt = createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }
}
