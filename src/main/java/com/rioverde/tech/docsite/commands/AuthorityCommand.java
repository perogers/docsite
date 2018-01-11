package com.rioverde.tech.docsite.commands;


import com.rioverde.tech.docsite.domain.State;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Getter
@Setter
@NoArgsConstructor
public class AuthorityCommand {

    private Long id;

    @Enumerated(EnumType.STRING)
    private State state = State.GA;

    @NotBlank
    private String county;

    private String city;

    @NotBlank
    private String url;

    @NotBlank
    private String agency;


}
