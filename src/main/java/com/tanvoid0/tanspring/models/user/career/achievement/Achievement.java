package com.tanvoid0.tanspring.models.user.career.achievement;

import com.tanvoid0.tanspring.models.user.career.Career;
import com.tanvoid0.tanspring.models.user.career.organization.Organization;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.io.Serial;
import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PostPersist;
import javax.persistence.Table;

@Getter
@Setter
@SuperBuilder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "career_achievement")
public class Achievement extends Organization implements Serializable {

  public static final String NAME = "Achievement";

  @Serial
  private static final long serialVersionUID = -3589624768657055223L;

  @Column(nullable = false, length = 3000)
  private String achievement;

  private Long orderSeq;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "career_id", nullable = false)
  private Career career;

  @PostPersist
  private void postPersist() {
    this.orderSeq = this.id;
  }
}
