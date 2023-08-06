package com.tanvoid0.tanspring.models.user.skill.entity;

import com.tanvoid0.tanspring.common.vo.BaseEntity;
import com.tanvoid0.tanspring.models.user.AppUser;
import com.tanvoid0.tanspring.models.user.skill.entity.hard.SkillHard;
import com.tanvoid0.tanspring.models.user.skill.entity.hard.framework.SkillFramework;
import com.tanvoid0.tanspring.models.user.skill.entity.linguistic.SkillLinguistic;
import com.tanvoid0.tanspring.models.user.skill.entity.soft.SkillSoft;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.io.Serial;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.OrderBy;

@Getter
@Setter
@SuperBuilder(toBuilder = true)
@AllArgsConstructor
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
  private Set<SkillFramework> frameworks;

  @OneToMany(mappedBy = "skill", cascade = CascadeType.ALL, orphanRemoval = true)
  @OrderBy("orderSeq ASC")
  private Set<SkillHard> hardSkills;

  @OneToMany(mappedBy = "skill", cascade = CascadeType.ALL, orphanRemoval = true)
  @OrderBy("orderSeq ASC")
  private Set<SkillSoft> softSkills;

  @OneToMany(mappedBy = "skill", cascade = CascadeType.ALL, orphanRemoval = true)
  @OrderBy("orderSeq ASC")
  private Set<SkillLinguistic> linguisticSkills;

  @OneToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "user_id", nullable = false)
  private AppUser user;

  public SkillEntity() {
    this.frameworks = new HashSet<>();
    this.hardSkills = new HashSet<>();
    this.softSkills = new HashSet<>();
    this.linguisticSkills = new HashSet<>();
  }
}
