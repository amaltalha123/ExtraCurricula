package com.cwa.springboot_app.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String fullname;
    private String username; // the username is the user's emal
    private String password;

    @Enumerated(EnumType.STRING)
    private UserType role;

   
}
