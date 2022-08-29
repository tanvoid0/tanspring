package com.tanvoid0.tanspring.models.util_entities.skill;

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
@Table(name = "skill_framework")
public class SkillFramework extends BaseSkill implements Serializable {

    @Serial
    private static final long serialVersionUID = -4351519351228269323L;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "framework", nullable = false)
    private SkillLanguage language;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "library", nullable = false)
    private SkillLibrary library;

    @Enumerated(EnumType.STRING)
    private PlatformType platform;
}
