package com.tanvoid0.tanspring.models;

import com.tanvoid0.tanspring.models.enums.PasswordType;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

import javax.validation.constraints.NotNull;

@Document(collection = "passwords")
public class PasswordModel {
  @Id
  private String id;
  @NotNull
  private String name;
  @NotNull
  private String password;
  private PasswordType passwordType = PasswordType.WEB;
  private String url;
  private String developer;
  private String userId;

  public PasswordModel(String name, String password, PasswordType passwordType, String url, String developer, String userId) {
    this.name = name;
    this.password = password;
    this.passwordType = passwordType;
    this.url = url;
    this.developer = developer;
    this.userId = userId;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public PasswordType getPasswordType() {
    return passwordType;
  }

  public void setPasswordType(PasswordType passwordType) {
    this.passwordType = passwordType;
  }

  public String getUrl() {
    return url;
  }

  public void setUrl(String url) {
    this.url = url;
  }

  public String getDeveloper() {
    return developer;
  }

  public void setDeveloper(String developer) {
    this.developer = developer;
  }

  public String getUserId() {
    return userId;
  }

  public void setUserId(String userId) {
    this.userId = userId;
  }
}
