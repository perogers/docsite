package com.rioverde.tech.docsite.docsite.domain;

import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Data
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String userId;

    @NotBlank
    private String firstName;

    @NotBlank
    private String lastName;

    // TODO address and contact information

    @Enumerated(EnumType.STRING)
    private UserType userType;

    @Enumerated(EnumType.STRING)
    private UserStatus userStatus;


}