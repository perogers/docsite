package com.rioverde.tech.docsite.converters;

import com.rioverde.tech.docsite.commands.AuthorityCommand;
import com.rioverde.tech.docsite.domain.Authority;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class AuthorityToAuthorityCommand implements Converter<Authority, AuthorityCommand>{

    @Override
    public AuthorityCommand convert(Authority authority) {

        AuthorityCommand command = new AuthorityCommand();
        command.setId(authority.getId());
        command.setCity(authority.getCity());
        command.setCounty(authority.getCounty());
        command.setState(authority.getState());
        command.setUrl(authority.getUrl());
        command.setAgency(authority.getAgency());

        return command;
    }
}
