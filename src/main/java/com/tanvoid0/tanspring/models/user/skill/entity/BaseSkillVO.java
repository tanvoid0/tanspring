package com.tanvoid0.tanspring.models.user.skill.entity;

import com.tanvoid0.tanspring.common.vo.BaseVO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.io.Serial;
import java.io.Serializable;

@Data
@SuperBuilder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
public class BaseSkillVO extends BaseVO implements Serializable {
  @Serial
  private static final long serialVersionUID = 3175190645374695164L;

  private String name;
  private String category;
  private String icon;
  private String image;
  private String fluency;
  private float fluencyVal;
  private String description;
}
