package com.kriger.guidinglight.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "roles")
public class Role {

    @Id
    @GeneratedValue
    private Long id;

    private String role;

    @ManyToMany( mappedBy = "roles")
    private Set<User> users = new HashSet<>();

    public Role(String role){
        this.role=role;
    }

}
