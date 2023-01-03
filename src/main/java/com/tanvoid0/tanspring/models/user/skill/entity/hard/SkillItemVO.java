package com.tanvoid0.tanspring.models.user.skill.entity.hard;

import com.tanvoid0.tanspring.models.user.skill.entity.BaseSkillVO;
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
public class SkillItemVO extends BaseSkillVO implements Serializable {

  @Serial
  private static final long serialVersionUID = -4982497023585950056L;
  private Long orderSeq;
}
