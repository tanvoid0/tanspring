package com.tanvoid0.tanspring.models.util_entities.tag;

import com.tanvoid0.tanspring.common.vo.BaseEntity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.io.Serial;
import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Getter
@Setter
@SuperBuilder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "tags")
public class Tag extends BaseEntity implements Serializable {
  @Serial
  private static final long serialVersionUID = -3314589135241224287L;

  @Column(nullable = false)
  private String title;

  private String icon;

  private String image;
}
