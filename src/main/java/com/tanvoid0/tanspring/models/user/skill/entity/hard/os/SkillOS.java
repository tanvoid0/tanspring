package com.tanvoid0.tanspring.models.user.skill.entity.hard.os;

import com.tanvoid0.tanspring.models.user.skill.entity.BaseSkill;
import com.tanvoid0.tanspring.models.user.skill.entity.Skill;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import java.io.Serial;
import java.io.Serializable;

@Getter
@Setter
@SuperBuilder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "skill_os")
public class SkillOS extends BaseSkill implements Serializable {
  @Serial
  private static final long serialVersionUID = -342902202425848279L;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "skill_id", nullable = false)
  private Skill skill;
}
