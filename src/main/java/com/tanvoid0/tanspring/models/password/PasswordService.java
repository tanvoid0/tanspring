package com.tanvoid0.tanspring.models.password;

import java.util.List;
import java.util.Optional;

public interface PasswordService {
  List<PasswordModel> findByNameContaining(String name);

  List<PasswordModel> findByUser(String id);

  Optional<PasswordModel> findById(String id);

  PasswordModel save(PasswordModel model);

  PasswordModel update(String id, PasswordModel model);

  void deleteById(String id);
}