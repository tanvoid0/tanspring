package com.tanvoid0.tanspring.models.user.skill.entity.hard.framework;

import com.tanvoid0.tanspring.models.user.skill.entity.BaseSkill;
import com.tanvoid0.tanspring.models.user.skill.entity.hard.framework.language.SkillLanguage;
import com.tanvoid0.tanspring.models.user.skill.entity.hard.framework.library.SkillLibrary;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import java.io.Serial;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@SuperBuilder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "skill_framework")
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
}
