package com.tanvoid0.tanspring.models.user.skill.entity.hard.framework.language;

import com.tanvoid0.tanspring.common.vo.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serial;
import java.io.Serializable;

@Getter
@Setter
@SuperBuilder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "skill_language")
public class SkillLanguage extends BaseEntity implements Serializable {
  @Serial
  private static final long serialVersionUID = -5562834924777077226L;

  @Column(nullable = false)
  private String title;

  private String icon;
  private String image;

  private String fluency;
  private float fluencyVal;

  private String description;
}
