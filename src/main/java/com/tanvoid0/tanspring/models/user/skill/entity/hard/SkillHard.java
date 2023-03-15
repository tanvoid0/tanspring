package com.tanvoid0.tanspring.models.user.skill.entity.hard;

import com.tanvoid0.tanspring.common.vo.BaseEntity;
import com.tanvoid0.tanspring.models.user.skill.entity.SkillEntity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.*;

@Getter
@Setter
@SuperBuilder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "skill_hard")
public class SkillHard extends BaseEntity implements Serializable {

  @Serial
  private static final long serialVersionUID = -514164160011319657L;

  @Column(nullable = false, unique = true)
  private String name;

  @OneToMany(mappedBy = "skill", cascade = CascadeType.ALL, orphanRemoval = true)
  @OrderBy("orderSeq ASC")
  private List<SkillItem> items = new ArrayList<>();

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "skill_id", nullable = false)
  private SkillEntity skill;

}
