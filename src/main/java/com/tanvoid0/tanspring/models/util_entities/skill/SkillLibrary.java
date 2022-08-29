package com.tanvoid0.tanspring.models.util_entities.skill;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.io.Serial;

@Getter
@Setter
@SuperBuilder(toBuilder = true)
@NoArgsConstructor
@Entity
@Table(name = "skill_library")
public class SkillLibrary extends BaseSkill {

    @Serial
    private static final long serialVersionUID = -7562581438755909746L;

    @OneToOne(fetch = FetchType.LAZY)
    private SkillFramework framework;
}
