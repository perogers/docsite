package com.rioverde.tech.docsite.converters;

import com.rioverde.tech.docsite.commands.AuthorityCommand;
import com.rioverde.tech.docsite.domain.Authority;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class AuthorityCommandToAuthority  implements Converter<AuthorityCommand, Authority> {

    @Override
    public Authority convert(AuthorityCommand command) {
        Authority authority = new Authority();
        authority.setId(command.getId());
        authority.setCity(command.getCity());
        authority.setCounty(command.getCounty());
        authority.setState(command.getState());
        authority.setUrl(command.getUrl());
        authority.setAgency(command.getAgency());

        return authority;
    }
}
