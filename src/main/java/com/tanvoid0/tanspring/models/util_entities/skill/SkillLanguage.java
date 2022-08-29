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
@Table(name = "skill_language")
public class SkillLanguage extends BaseSkill implements Serializable {
    @Serial
    private static final long serialVersionUID = -5562834924777077226L;

    @OneToOne(fetch = FetchType.LAZY)
    private SkillFramework framework;
}
