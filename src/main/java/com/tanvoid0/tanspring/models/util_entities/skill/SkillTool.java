package com.tanvoid0.tanspring.models.util_entities.skill;

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
@Table(name = "skill_tool")
public class SkillTool extends BaseSkill implements Serializable {

    @Serial
    private static final long serialVersionUID = 5317549348259499110L;

    @Column(nullable = false)
    private String type;
}
