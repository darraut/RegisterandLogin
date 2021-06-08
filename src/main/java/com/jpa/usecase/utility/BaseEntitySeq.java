package com.jpa.usecase.utility;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@MappedSuperclass
@Data
public class BaseEntitySeq {

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="CREATED_DATE", updatable = false)
    @JsonIgnore
    private Date createdDate;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="UPDATED_DATE")
    @JsonIgnore
    private Date updatedDate;

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public Date getUpdatedDate() {
        return updatedDate;
    }

    public void setUpdatedDate(Date updatedDate) {
        this.updatedDate = updatedDate;
    }

    @PreUpdate
    public void setPreUpdateEntity() {
        this.updatedDate = new Date();
    }

    @PrePersist
    public void setPrePersistEntity() {
        this.createdDate = new Date();
        this.updatedDate = new Date();
    }
}
