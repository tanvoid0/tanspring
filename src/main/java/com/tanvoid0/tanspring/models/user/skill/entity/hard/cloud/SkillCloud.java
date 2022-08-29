package com.tanvoid0.tanspring.models.user.skill.entity.hard.cloud;

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
@Table(name = "skill_cloud")
public class SkillCloud extends BaseSkill implements Serializable {
  @Serial
  private static final long serialVersionUID = 3644045565059676535L;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "skill_id", nullable = false)
  private Skill skill;
}
