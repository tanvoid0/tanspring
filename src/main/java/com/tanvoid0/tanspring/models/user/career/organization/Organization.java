package com.tanvoid0.tanspring.models.user.career.organization;

import com.tanvoid0.tanspring.common.vo.BaseEntity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.io.Serial;
import java.io.Serializable;

import jakarta.persistence.*;

@Getter
@Setter
@SuperBuilder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "career_organization")
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Organization extends BaseEntity implements Serializable {
  @Serial
  private static final long serialVersionUID = -557593147072260070L;

  public static final String BASE_NAME = "Organization";

  @Column(nullable = false)
  private String _type;

  @Column(nullable = false)
  private String title;

  private String image;

  private String logo;
  private String institution;
  private String address;
  private String timeline;

  @Column(length = 3000)
  private String description;

  @Column(length = 3000)
  private String activities;

}
