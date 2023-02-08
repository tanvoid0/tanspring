package com.tanvoid0.tanspring.models.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
public class UpdateUserInfoVO {
  private long version;
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
  private String email;
  private String publicEmail;
  private String cv;
  private String url;
}
