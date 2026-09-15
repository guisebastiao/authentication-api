package br.com.guisebastiao.authenticationapi.domain.model;

import br.com.guisebastiao.authenticationapi.domain.valueobject.*;

public class Role {
    private Id id;
    private RoleName name;
    private Description description;
    private Timestamp createdAt;
    private Timestamp updatedAt;

    public Role() {}

    public Role(Id id, RoleName name, Description description, Timestamp createdAt, Timestamp updatedAt) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public Id getId() {
        return id;
    }

    public void setId(Id id) {
        this.id = id;
    }

    public RoleName getName() {
        return name;
    }

    public void setName(RoleName name) {
        this.name = name;
    }

    public Description getDescription() {
        return description;
    }

    public void setDescription(Description description) {
        this.description = description;
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
