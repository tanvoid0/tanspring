package com.tanvoid0.tanspring.models.user.career.experience;

import com.tanvoid0.tanspring.models.user.career.organization.Organization;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.PostPersist;
import javax.persistence.Table;
import java.io.Serial;
import java.io.Serializable;

@Getter
@Setter
@SuperBuilder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "career_experience")
public class Experience extends Organization implements Serializable {
  public static final String NAME = "Experience";

  @Serial
  private static final long serialVersionUID = -1750320303362125624L;

  @Column(nullable = false)
  private String role;

  private Long orderSeq;

  @PostPersist
  private void postPersist() {
    this.orderSeq = this.id;
  }
}
