package com.apirest.entity;

import com.apirest.enums.RolName;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
@Data
@Entity
@Table(name = "role")
public class Rol {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotNull
    @Column(length = 60)
    @Enumerated(EnumType.STRING)
    private RolName name;
}
