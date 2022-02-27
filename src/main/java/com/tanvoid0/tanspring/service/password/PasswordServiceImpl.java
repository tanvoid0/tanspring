package com.tanvoid0.tanspring.service.password;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import com.tanvoid0.tanspring.security.AuthService;
import com.tanvoid0.tanspring.security.AuthServiceImpl;
import com.tanvoid0.tanspring.service.core.AttributeEncryptor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("passwordService")
@RequiredArgsConstructor(onConstructor_ = { @Autowired })
@Slf4j
@Transactional
public class PasswordServiceImpl implements PasswordService {
  @NonNull
  private PasswordRepository passwordRepository;

  @NonNull
  private AuthService authService;

  @Override
  public List<PasswordModel> findByNameContaining(String name) {
    List<PasswordModel> list = new ArrayList<PasswordModel>();
    if (name == null) {
      passwordRepository.findAll().forEach(list::add);
    } else {
      passwordRepository.findByNameContaining(name).forEach(list::add);
    }

    if (list.isEmpty()) {
      return null;
    }

    return list;
  }

  @Override
  public List<PasswordModel> findByUser(String id) {
    List<PasswordModel> list = new ArrayList<PasswordModel>();
    passwordRepository.findPasswordByUserId(id).forEach(data -> {
      String pass = AttributeEncryptor.decrypt(data.getPassword());
      data.setPassword(pass);
      list.add(data);
    });

    if (list.isEmpty()) {
      return null;
    }
    return list;

  }

  @Override
  public Optional<PasswordModel> findById(String id) {
    Optional<PasswordModel> pass = passwordRepository.findById(id);
    if (pass.isPresent()) {
      pass.get().setPassword(AttributeEncryptor.decrypt(pass.get().getPassword()));
      return pass;
    }
    return Optional.empty();
  }

  @Override
  public PasswordModel save(PasswordModel model) {
    String userId = AuthServiceImpl.getId();
    model.setUserId(userId);
    model.setPassword(AttributeEncryptor.encrypt(model.getPassword()));
    // PasswordModel data = new PasswordModel(
    //     model.getName(),
    //     model.getPassword(),
    //     model.getPasswordType(),
    //     model.getUrl(),
    //     model.getDeveloper(),
    //     userId
    // );
    return passwordRepository.save(model);
  }

  @Override
  public PasswordModel update(String id, PasswordModel model) {
    Optional<PasswordModel> data = passwordRepository.findById(id);

    if (data.isPresent()) {
      PasswordModel updatedData = data.get();
      updatedData.setName(model.getName());
      updatedData.setUsername(model.getUsername());
      updatedData.setPassword(AttributeEncryptor.encrypt(model.getPassword()));
      updatedData.setPasswordType(model.getPasswordType());
      updatedData.setUrl(model.getUrl());
      updatedData.setDeveloper(model.getDeveloper());
      passwordRepository.save(updatedData);
      return updatedData;
    } else {
      return null;
    }
  }

  @Override
  public void deleteById(String id) {
    passwordRepository.deleteById(id);
  }
}