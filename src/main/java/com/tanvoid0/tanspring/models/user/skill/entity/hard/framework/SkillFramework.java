package com.tanvoid0.tanspring.models.user.skill.entity.hard.framework;

import com.tanvoid0.tanspring.models.user.skill.entity.BaseSkill;
import com.tanvoid0.tanspring.models.user.skill.entity.SkillEntity;
import com.tanvoid0.tanspring.models.user.skill.entity.hard.framework.language.SkillLanguage;
import com.tanvoid0.tanspring.models.user.skill.entity.hard.framework.library.SkillLibrary;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.io.Serial;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Getter
@Setter
@SuperBuilder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "skill_framework", uniqueConstraints = {
    @UniqueConstraint(columnNames = {"name"})
})
public class SkillFramework extends BaseSkill implements Serializable {

  @Serial
  private static final long serialVersionUID = -4351519351228269323L;

  @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
  @JoinTable(name = "skill_framework_languages",
      joinColumns = @JoinColumn(name = "framework_id", referencedColumnName = "id"),
      inverseJoinColumns = @JoinColumn(name = "language_id", referencedColumnName = "id"))
  private Set<SkillLanguage> languages = new HashSet<>();

  @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
  @JoinTable(name = "skill_framework_libraries",
      joinColumns = @JoinColumn(name = "framework_id", referencedColumnName = "id"),
      inverseJoinColumns = @JoinColumn(name = "library_id", referencedColumnName = "id"))
  private Set<SkillLibrary> library = new HashSet<>();

  @Enumerated(EnumType.STRING)
  @Column(nullable = false)
  private PlatformType platform;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "skill_id", nullable = false)
  private SkillEntity skill;
}
