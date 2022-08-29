package com.tanvoid0.tanspring.models.util_entities.skill;

import com.tanvoid0.tanspring.common.vo.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.io.Serial;
import java.io.Serializable;

@Getter
@Setter
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
@SuperBuilder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
public abstract class BaseSkill extends BaseEntity implements Serializable {

    @Serial
    private static final long serialVersionUID = 231754022445340069L;

    @Column(nullable = false)
    private String title;

    private String icon;
    private String description;
}
