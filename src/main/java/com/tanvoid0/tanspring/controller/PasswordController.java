package com.tanvoid0.tanspring.controller;

import com.tanvoid0.tanspring.models.PasswordModel;
import com.tanvoid0.tanspring.repository.PasswordRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/password")
public class PasswordController {
  @Autowired PasswordRepository passwordRepository;

  @GetMapping
  public ResponseEntity<List<PasswordModel>> getPasswordRepository(@RequestParam(required = false) String name) {
    try {
      List<PasswordModel> data = new ArrayList<PasswordModel>();
      if (name == null)
        passwordRepository.findAll().forEach(data::add);
      else
        passwordRepository.findByNameContaining(name).forEach(data::add);

      if (data.isEmpty()) {
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
      }

      return new ResponseEntity<>(data, HttpStatus.OK);
    } catch (Exception e) {
      return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  @GetMapping("/{id}")
  public ResponseEntity<PasswordModel> getById(@PathVariable("id") String id) {
    Optional<PasswordModel> data = passwordRepository.findById(id);

    return data.map(passwordModel -> new ResponseEntity<>(passwordModel, HttpStatus.OK))
        .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
  }

  @PostMapping
  public ResponseEntity<PasswordModel> create(@RequestBody PasswordModel model) {
    try {
      PasswordModel data = new PasswordModel(
          model.getName(),
          model.getPassword(),
          model.getPasswordType(),
          model.getUrl(),
          model.getDeveloper(),
          model.getUserId()
      );
      PasswordModel savedData = passwordRepository.save(data);
      return new ResponseEntity<>(savedData, HttpStatus.CREATED);
    } catch (Exception e) {
      return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  @PutMapping("/{id}")
  public ResponseEntity<PasswordModel> update(@PathVariable("id") String id, @RequestBody PasswordModel model) {
    Optional<PasswordModel> data = passwordRepository.findById(id);

    if (data.isPresent()) {
      PasswordModel updatedData = data.get();
      updatedData.setName(model.getName());
      updatedData.setPassword(model.getPassword());
      updatedData.setPasswordType(model.getPasswordType());
      updatedData.setUrl(model.getUrl());
      updatedData.setDeveloper(model.getDeveloper());

      return new ResponseEntity<>(passwordRepository.save(updatedData), HttpStatus.OK);
    } else {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<HttpStatus> delete(@PathVariable("id") String id) {
    try {
      passwordRepository.deleteById(id);
      return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    } catch (Exception e) {
      return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }
}
