package com.tanvoid0.tanspring.service.password;

import com.tanvoid0.tanspring.enums.PasswordType;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@Document(collection = "passwords")
public class PasswordModel {
  @Id
  private String id;
  @NotNull
  private String name;
  @NotNull
  private String username;
  @NotNull
  private String password;
  private PasswordType passwordType = PasswordType.WEB;
  private String url;
  private String developer;
  private String userId;
}