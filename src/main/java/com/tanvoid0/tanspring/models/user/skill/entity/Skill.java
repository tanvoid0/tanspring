package com.tanvoid0.tanspring.models.user.skill.entity;

import com.tanvoid0.tanspring.common.vo.BaseEntity;
import com.tanvoid0.tanspring.models.user.User;
import com.tanvoid0.tanspring.models.user.skill.entity.hard.cloud.SkillCloud;
import com.tanvoid0.tanspring.models.user.skill.entity.hard.linguistic.SkillLinguistic;
import com.tanvoid0.tanspring.models.user.skill.entity.hard.os.SkillOS;
import com.tanvoid0.tanspring.models.user.skill.entity.hard.software.SkillSoftware;
import com.tanvoid0.tanspring.models.user.skill.entity.soft.SkillSoft;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import java.io.Serial;
import java.io.Serializable;
import java.util.Set;

@Getter
@Setter
@SuperBuilder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Skill extends BaseEntity implements Serializable {

  @Serial
  private static final long serialVersionUID = 1074405926970595615L;

  @OneToMany(mappedBy = "skill", cascade = CascadeType.ALL, orphanRemoval = true)
  private Set<SkillCloud> cloud;

  @OneToMany(mappedBy = "skill", cascade = CascadeType.ALL, orphanRemoval = true)
  private Set<SkillLinguistic> linguistic;

  @OneToMany(mappedBy = "skill", cascade = CascadeType.ALL, orphanRemoval = true)
  private Set<SkillOS> os;

  @OneToMany(mappedBy = "skill", cascade = CascadeType.ALL, orphanRemoval = true)
  private Set<SkillSoftware> software;

  @OneToMany(mappedBy = "skill", cascade = CascadeType.ALL, orphanRemoval = true)
  private Set<SkillSoft> softSkill;

  @OneToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "user_id", nullable = false)
  private User user;
}
