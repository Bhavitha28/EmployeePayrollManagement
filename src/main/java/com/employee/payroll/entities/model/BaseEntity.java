package com.employee.payroll.entities.model;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Date;

public class BaseEntity {
    @Column(name="is_active")
    private Boolean isActive=true;


    @Column(name="created_on")
    public LocalDateTime createdOn;

    @Column(name="last_modified_on")
    private LocalDateTime lastModifiedOn;

    public Boolean getActive() {
        return isActive;
    }

    public void setActive(Boolean active) {
        isActive = active;
    }

    public LocalDateTime getCreatedOn() {
        return createdOn;
    }

    @PrePersist
    public void setCreatedOn( ) {
        this.createdOn = LocalDateTime.now();
    }

    public LocalDateTime getLastModifiedOn() {
        return lastModifiedOn;
    }

    @PreUpdate
    public void setLastModifiedOn() {
        this.lastModifiedOn = LocalDateTime.now();
    }


}
