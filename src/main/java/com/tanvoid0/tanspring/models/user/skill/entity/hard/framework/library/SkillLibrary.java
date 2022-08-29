package com.tanvoid0.tanspring.models.user.skill.entity.hard.framework.library;

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
@Table(name = "skill_library")
public class SkillLibrary extends BaseEntity implements Serializable {

  @Serial
  private static final long serialVersionUID = 8030219300219672921L;

  @Column(nullable = false)
  private String title;

  private String icon;
  private String image;

  private String fluency;
  private float fluencyVal;

  private String description;
}
