package com.tanvoid0.tanspring.models.user.skill.entity;

import com.tanvoid0.tanspring.common.vo.BaseEntity;
import com.tanvoid0.tanspring.models.user.User;
import com.tanvoid0.tanspring.models.user.skill.entity.hard.SkillHard;
import com.tanvoid0.tanspring.models.user.skill.entity.hard.framework.SkillFramework;
import com.tanvoid0.tanspring.models.user.skill.entity.linguistic.SkillLinguistic;
import com.tanvoid0.tanspring.models.user.skill.entity.soft.SkillSoft;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.io.Serial;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;

@Getter
@Setter
@SuperBuilder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class SkillEntity extends BaseEntity implements Serializable {

  @Serial
  private static final long serialVersionUID = 1074405926970595615L;

//  @OneToMany(mappedBy = "skill", cascade = CascadeType.ALL, orphanRemoval = true)
//  private Set<SkillCloud> cloud;
//
//  @OneToMany(mappedBy = "skill", cascade = CascadeType.ALL, orphanRemoval = true)
//  private Set<SkillLinguistic> linguistic;
//
//  @OneToMany(mappedBy = "skill", cascade = CascadeType.ALL, orphanRemoval = true)
//  private Set<SkillOS> os;
//
//  @OneToMany(mappedBy = "skill", cascade = CascadeType.ALL, orphanRemoval = true)
//  private Set<SkillSoftware> software;

  @OneToMany(mappedBy = "skill", cascade = CascadeType.ALL, orphanRemoval = true)
  @OrderBy("orderSeq ASC")
  private Set<SkillFramework> frameworks = new HashSet<>();

  @OneToMany(mappedBy = "skill", cascade = CascadeType.ALL, orphanRemoval = true)
  @OrderBy("orderSeq ASC")
  private Set<SkillHard> hardSkills = new HashSet<>();

  @OneToMany(mappedBy = "skill", cascade = CascadeType.ALL, orphanRemoval = true)
  @OrderBy("orderSeq ASC")
  private Set<SkillSoft> softSkills = new HashSet<>();

  @OneToMany(mappedBy = "skill", cascade = CascadeType.ALL, orphanRemoval = true)
  @OrderBy("orderSeq ASC")
  private Set<SkillLinguistic> linguisticSkills = new HashSet<>();

  @OneToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "user_id", nullable = false)
  private User user;
}
