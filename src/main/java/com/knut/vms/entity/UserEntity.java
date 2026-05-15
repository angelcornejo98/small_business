package com.knut.vms.entity;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

public class UserEntity {
    @Id
    @GeneratedValue
    private Long id;

    private String username;
    private String password;
    private String role;
}
