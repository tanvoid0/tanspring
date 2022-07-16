package com.tanvoid0.tanspring.models.password;

import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface PasswordRepository extends MongoRepository<PasswordModel, String> {
  List<PasswordModel> findByNameContaining(String name);

  List<PasswordModel> findPasswordByUserId(String id);
}