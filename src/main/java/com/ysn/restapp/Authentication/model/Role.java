package com.ysn.restapp.Authentication.model;

import com.ysn.restapp.model.BaseEntity;
import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "roles")
@Data
public class Role extends BaseEntity {

    @Enumerated(EnumType.STRING)
    @Column(length = 20,nullable = false)
    private ERole name;
}
