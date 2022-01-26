package com.tanvoid0.tanspring.service;

import lombok.AllArgsConstructor;

import com.tanvoid0.tanspring.models.PasswordModel;
import com.tanvoid0.tanspring.repository.PasswordRepository;

import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class PasswordService {
  private final PasswordRepository passwordRepository;

  public List<PasswordModel> getAllPasswords() {
    return passwordRepository.findAll();
  }
}
