package com.vitdo82.paa.serviceapi.user.repository;

import com.vitdo82.paa.serviceapi.core.repository.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "users")
@Getter
@Setter
public class User extends BaseEntity {

    private String username;
    private String password;
    private String firstName;
    private String lastName;
    private String email;
}
