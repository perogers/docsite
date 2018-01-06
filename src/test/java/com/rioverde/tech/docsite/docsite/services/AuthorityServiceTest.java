package com.rioverde.tech.docsite.docsite.services;

import com.rioverde.tech.docsite.docsite.domain.Authority;
import com.rioverde.tech.docsite.docsite.domain.State;
import com.rioverde.tech.docsite.docsite.repositories.AuthorityPagingRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Sort;

import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.*;

public class AuthorityServiceTest {

    AuthorityServiceImpl authorityService;

    @Mock
    AuthorityPagingRepository authorityRepository;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        authorityService = new AuthorityServiceImpl(authorityRepository);
    }

    @Test
    public void getAuthorityByIdTest() throws Exception{
        // Given
        Long id = new Long(2L);
        Authority authority = new Authority();
        authority.setId(id);
        when(authorityRepository.findOne(id)).thenReturn(authority);

        // When
        Authority authorityReturned = authorityService.findById(id);

        // Then
        assertNotNull("Failed to return authority", authorityReturned);
        verify(authorityRepository, times(1)).findOne(id);
        verify(authorityRepository, never()).findAll();
    }

    @Test
    public void getAuthoritiesTest() {
        // Given
        Long id = new Long(2L);
        Authority authority = new Authority();
        authority.setId(id);
        authority.setState(State.GA);
        authority.setCounty("County");
        Set<Authority> authoritySet = new HashSet<>();
        authoritySet.add(authority);
        Sort sort = new Sort(Sort.DEFAULT_DIRECTION, "state", "county", "city");

        when(authorityRepository.findAll(sort))
                .thenReturn(authoritySet);

        // When
        Set<Authority> authorities = authorityService.getAuthorities();
        assertEquals(authorities.size(), 1);
        verify(authorityRepository, times(1)).findAll(sort);
        verify(authorityRepository, never()).findOne(anyLong());
    }

}


