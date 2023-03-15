package com.tanvoid0.tanspring.models.user.skill.entity;

import com.tanvoid0.tanspring.common.vo.BaseEntity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.io.Serial;
import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;

@Getter
@Setter
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
@SuperBuilder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
public abstract class BaseSkill extends BaseEntity implements Serializable {

  @Serial
  private static final long serialVersionUID = -5515796402037749601L;

  @Column(nullable = false, unique = true)
  private String name;

  private String category;

  private String icon;
  private String image;

  private String fluency;
  private float fluencyVal;

  private String description;

}
