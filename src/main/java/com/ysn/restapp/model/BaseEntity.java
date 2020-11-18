package com.ysn.restapp.model;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Date;

@Data
@MappedSuperclass
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class BaseEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id")
    private Long id;

    @Size(max = 12)
    @Column(nullable = false, columnDefinition = "Bit(1) default false")
    private boolean isDeleted = false;

    @Size(max = 12)
    @Column(nullable = false, columnDefinition = "Bit(1) default True")
    private boolean isEnabled = true;

    @Size(max = 12)
    @Column(nullable = false, columnDefinition = "Bit(1) default True")
    private boolean isLoced = true;

    @Column(nullable = false)
    @Size(max = 120)
    private String dataChangeCreatedBy;

    @Column(nullable = false)
    @Size(max = 120)
    private Date dataChangeCreatedTime;

    @Size(max = 120)
    private String dataChangeLastModifiedBy;

    @Size(max = 120)
    private Date dataChangeLastModifiedTime;

    @PrePersist
    protected void prePersist() {
        if (this.dataChangeCreatedTime == null) dataChangeCreatedTime = new Date();
        if (this.dataChangeLastModifiedTime == null) dataChangeLastModifiedTime = new Date();
        if (this.dataChangeCreatedBy == null) {

            dataChangeCreatedBy ="Manuel Create User";

        }
    }

    @PreUpdate
    protected void preUpdate() {
        this.dataChangeLastModifiedTime = new Date();
    }

    @PreRemove
    protected void preRemove() {
        this.dataChangeLastModifiedTime = new Date();
    }
}
