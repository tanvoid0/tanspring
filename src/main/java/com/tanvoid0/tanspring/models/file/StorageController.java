package com.tanvoid0.tanspring.models.file;

import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/api/v1/file")
@RequiredArgsConstructor
public class StorageController {

  private final StorageService service;

  @GetMapping("/local/{filename}")
  public String downloadFileLocally(@PathVariable("filename") final String filename) throws IOException {
    return service.readFileFromStorage(filename).toString();
  }

  @PostMapping("/local")
  public UserFileDataVO uploadFileLocally(@RequestParam("file") final MultipartFile file) throws IOException {
    return service.uploadFileToStorage(file);
  }


  @DeleteMapping("/local/{filename}")
  public void deleteFileLocally(@PathVariable("filename") final String filename) throws IOException {
    service.deleteFileFromStorage(filename);
  }
}
