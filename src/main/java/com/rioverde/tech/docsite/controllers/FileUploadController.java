package com.rioverde.tech.docsite.controllers;

import com.rioverde.tech.docsite.exceptions.StorageFileNotFoundException;
import com.rioverde.tech.docsite.services.DocumentStorageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.util.stream.Collectors;

@Slf4j
@Controller
public class FileUploadController {

    private final DocumentStorageService storageService;

    @Autowired
    public FileUploadController(DocumentStorageService storageService) {
        this.storageService = storageService;
    }

//  @GetMapping("/")
//  public String listUploadedFiles(Model model) throws IOException {
//
//      model.addAttribute("files", storageService.loadAll().map(
//              path -> MvcUriComponentsBuilder.fromMethodName(FileUploadController.class,
//                      "serveFile", path.getFileName().toString()).build().toString())
//              .collect(Collectors.toList()));
//
//      return "uploadForm";
//  }

    @GetMapping("/files/{filename:.+}")
    @ResponseBody
    public ResponseEntity<Resource> serveFile(@PathVariable String filename) {

        Resource file = storageService.loadAsResource(filename);
        return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION,
                "attachment; filename=\"" + file.getFilename() + "\"").body(file);
    }

    @PostMapping("/")
    public String handleFileUpload(@RequestParam("file") MultipartFile file,
                                   RedirectAttributes redirectAttributes) {

        storageService.store(file);
        redirectAttributes.addFlashAttribute("message",
                "You successfully uploaded " + file.getOriginalFilename() + "!");

        return "redirect:/";
    }

    @ExceptionHandler(StorageFileNotFoundException.class)
    public ResponseEntity<?> handleStorageFileNotFound(StorageFileNotFoundException exc) {
        return ResponseEntity.notFound().build();
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Exception.class)
    public ModelAndView handleException(Exception exception){

        log.error("Handling Exception");
        log.error(exception.getMessage());

        ModelAndView modelAndView = new ModelAndView();

        modelAndView.setViewName("500error");
        modelAndView.addObject("exception", exception);

        return modelAndView;
    }

}