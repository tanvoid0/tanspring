package com.tanvoid0.tanspring.models.user.skill.entity.hard;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
public class NewSkillItemVO {
  private String name;
  private String category;
  private String icon;
  private String image;
  private String fluency;
  private float fluencyVal;
  private String description;
}
