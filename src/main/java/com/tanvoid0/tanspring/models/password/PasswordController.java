package com.tanvoid0.tanspring.models.password;

import com.tanvoid0.tanspring.security.AuthServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/api/password")
public class PasswordController {
  @Autowired
  PasswordRepository passwordRepository;

  @Autowired
  PasswordService passwordService;

  // @Autowired
  // Authentication auth = SecurityContextHolder.getContext().getAuthentication();

  @GetMapping
  @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
  public ResponseEntity<List<PasswordModel>> getAllOrGetByName(@RequestParam(required = false) String name) {
    try {
      List<PasswordModel> data = passwordService.findByUser(AuthServiceImpl.getId());
      // List<PasswordModel> data = passwordService.findByNameContaining(name);

      if (data.isEmpty()) {
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
      }

      return new ResponseEntity<>(data, HttpStatus.OK);
    } catch (Exception e) {
      return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  @GetMapping("/{id}")
  @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
  public ResponseEntity<PasswordModel> getById(@PathVariable("id") String id) {
    Optional<PasswordModel> data = passwordService.findById(id);

    return data.map(passwordModel -> new ResponseEntity<>(passwordModel, HttpStatus.OK))
        .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
  }

  @PostMapping
  @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
  @ResponseBody
  public ResponseEntity<PasswordModel> create(@RequestBody PasswordModel model) {
    try {
      System.out.println("Creating new Password");
      PasswordModel data = passwordService.save(model);
      return new ResponseEntity<>(data, HttpStatus.CREATED);
    } catch (Exception e) {
      return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  @PutMapping("/{id}")
  public ResponseEntity<PasswordModel> update(@PathVariable("id") String id, @RequestBody PasswordModel model) {
    PasswordModel data = passwordService.update(id, model);
    if (data == null) {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    return new ResponseEntity<>(data, HttpStatus.OK);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<HttpStatus> delete(@PathVariable("id") String id) {
    try {
      passwordService.deleteById(id);
      return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    } catch (Exception e) {
      return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }
}