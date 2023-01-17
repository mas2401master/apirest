package com.apirest.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="users",  uniqueConstraints = { @UniqueConstraint(columnNames = { "username" } ),@UniqueConstraint(columnNames = { "email" }) })
@Data
public class UserApp {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private long id;
        private String name;
        private String username;
        private String email;
        private String password;

        @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
        @JoinTable(name = "user_rols", joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "rol_id", referencedColumnName = "id"))
        private Set<Rol> rols = new HashSet<>();

}

