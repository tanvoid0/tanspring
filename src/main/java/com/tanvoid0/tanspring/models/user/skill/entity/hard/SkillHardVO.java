package com.tanvoid0.tanspring.models.user.skill.entity.hard;

import com.tanvoid0.tanspring.common.vo.BaseVO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.HashSet;
import java.util.Set;

@Data
@SuperBuilder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
public class SkillHardVO extends BaseVO {
  private String name;
  private Set<SkillItemVO> items = new HashSet<>();
  private Long orderSec;
}
