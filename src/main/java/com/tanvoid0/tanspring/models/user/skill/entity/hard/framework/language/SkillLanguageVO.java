package com.tanvoid0.tanspring.models.user.skill.entity.hard.framework.language;

import com.tanvoid0.tanspring.common.vo.BaseVO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.io.Serial;
import java.io.Serializable;

@Getter
@Setter
@SuperBuilder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
public class SkillLanguageVO extends BaseVO implements Serializable {
  @Serial
  private static final long serialVersionUID = 7085181618051621015L;

  private String name;
  private String category;
  private String icon;
  private String image;
  private String fluency;
  private float fluencyVal;
  private String description;
  private Long orderSec;
}
