package com.rioverde.tech.docsite.docsite.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Data
@EqualsAndHashCode(exclude = "documents")
@Entity
public class Authority {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private State state;

    @NotBlank
    private String county;

    private String city;

    @OneToMany(mappedBy = "authority")
    private Set<Document> documents = new HashSet<>();
}
