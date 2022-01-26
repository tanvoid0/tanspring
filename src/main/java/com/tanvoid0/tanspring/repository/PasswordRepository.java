package com.tanvoid0.tanspring.repository;

import com.tanvoid0.tanspring.models.PasswordModel;

import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface PasswordRepository extends MongoRepository<PasswordModel, String> {
  List<PasswordModel> findByNameContaining(String name);
}
