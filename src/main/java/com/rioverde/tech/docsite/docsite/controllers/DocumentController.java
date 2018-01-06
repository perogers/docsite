package com.rioverde.tech.docsite.docsite.controllers;

import com.rioverde.tech.docsite.docsite.services.DocumentService;
import com.sun.org.apache.xpath.internal.operations.Mod;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@Controller
public class DocumentController {

    private final DocumentService documentService;

    public DocumentController(DocumentService documentService) {
        this.documentService = documentService;
    }

    @RequestMapping({"/documents"})
    public String getDocuments(Model model) {
        log.debug("Getting all documents");

        model.addAttribute("documents", documentService.getDocuments());

        return "documents/list";
    }
}
