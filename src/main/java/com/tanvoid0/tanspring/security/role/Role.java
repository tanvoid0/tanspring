package com.tanvoid0.tanspring.security.role;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import com.tanvoid0.tanspring.security.role.ERole;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@SuperBuilder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "roles")
public class Role {
  @Id
  private String id;

  private ERole name;

  public Role(ERole name) {
    this.name = name;
  }
}