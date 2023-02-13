package com.tanvoid0.tanspring.security.auth;

import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;

import jakarta.validation.constraints.NotNull;

@Data
public class NewUserVO {
  public static final String NAME = "NewUser";
  @Setter(AccessLevel.NONE)
  private final String _type = NAME;

  private String name;
  private String fullName;
  private String phone;
  private String avatar;
  private String coverImg;
  private int yob; // year of birth
  private String address;
  private String degree;
  private String title;
  private String titles;
  private String whatIDo;
  private String about;
  private String aboutDetailed;

  @NotNull
  private String username;

  @NotNull
  private String email;

  private String publicEmail;
  private String cv;
  private String url;

  @NotNull
  private String password;

  private Role role = Role.USER;
}
