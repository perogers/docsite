package com.rioverde.tech.docsite.docsite.bootstrap;

import com.rioverde.tech.docsite.docsite.domain.*;
import com.rioverde.tech.docsite.docsite.repositories.AuthorityPagingRepository;
import com.rioverde.tech.docsite.docsite.repositories.DocumentRepository;
import com.rioverde.tech.docsite.docsite.repositories.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Optional;

@Slf4j
@Component
public class DevBootStrap implements ApplicationListener<ContextRefreshedEvent> {

    @Value("classpath:/static/gre_research_validity_data.pdf")
    private Resource documentResource;
    private AuthorityPagingRepository authorityRepository;
    private DocumentRepository documentRepository;
    private UserRepository userRepository;

    public DevBootStrap(AuthorityPagingRepository authorityRepository,
                        DocumentRepository documentRepository,
                        UserRepository userRepository) {
        this.authorityRepository = authorityRepository;
        this.documentRepository = documentRepository;
        this.userRepository = userRepository;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        initData();
    }

    private void initData() {
        log.debug("Initializing dev data");

        // Verify Authorities
        Optional<Authority> dekalbGa = authorityRepository.findAuthorityByStateAndCounty(State.GA, "Dekalb");
        if(!dekalbGa.isPresent()) {
            throw new RuntimeException("Dekalb GA is not present");
        }

        Optional<Authority> nola = authorityRepository.findAuthorityByStateAndCounty(State.LA, "County");
        if(!nola.isPresent()) {
            throw new RuntimeException("New Orleans LA is not present");
        }

        // Verify Users
        Optional<User> adminUser = userRepository.findByUserIdAndAndUserStatus("br549", UserStatus.ACTIVE);
        if(!adminUser.isPresent()) {
            throw new RuntimeException("User for ID br549 is not present");
        }


        Optional<User> adminUser2 = userRepository.findByUserIdAndAndUserStatus("xxyzzy", UserStatus.ACTIVE);
        if(!adminUser2.isPresent()) {
            throw new RuntimeException("User for ID xxyzzy is not present");
        }

        Byte[] documentBytes = getDocument(documentResource);


        log.debug("Creating a Dekalb County GA document");
        Document dekalbGaDocument = new Document();
        dekalbGaDocument.setAuthority(dekalbGa.get());
        dekalbGaDocument.setDocument(documentBytes);
        dekalbGaDocument.setCreatedBy(adminUser.get());
        dekalbGaDocument.setUpdatedBy(adminUser.get());
        documentRepository.save(dekalbGaDocument);

        log.debug("Creating a New Orleans LA document");
        Document nolaDocument = new Document();
        nolaDocument.setAuthority(nola.get());
        nolaDocument.setDocument(documentBytes);
        nolaDocument.setCreatedBy(adminUser2.get());
        nolaDocument.setUpdatedBy(adminUser2.get());
        documentRepository.save(nolaDocument);

    }



    private Byte[] getDocument(Resource documentResource) {
        Byte[] documentBytes = null;

        try {
            byte[] bytes = Files.readAllBytes(Paths.get(documentResource.getURI()));
            documentBytes = new Byte[bytes.length];
            Arrays.setAll(documentBytes, n -> bytes[n]);
        }
        catch (IOException e) {
            throw new RuntimeException(e);
        }
        return documentBytes;
    }
}
