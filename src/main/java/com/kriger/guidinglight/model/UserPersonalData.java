package com.kriger.guidinglight.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.UniqueElements;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "users_personal_data")
public class UserPersonalData {

    @Id
    @GeneratedValue
    private Long id;

    private String nickName;

    private String firstName;
    private String lastName;

    private String country;
    private String city;

    @OneToOne
    @JsonIgnore
    private User user;

}
