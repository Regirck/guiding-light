package com.kriger.guidinglight.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue
    private Long id;

    @NotNull
    private String username;
    private String firstName;
    private String lastName;

    private String country;

    private String jobs;

    @NotNull
    private String email;
    @NotNull
    private String password;

    private LocalDateTime registrationDate;
    private LocalDateTime lastSignIn;

}







