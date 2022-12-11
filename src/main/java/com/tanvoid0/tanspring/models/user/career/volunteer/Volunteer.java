package com.tanvoid0.tanspring.models.user.career.volunteer;


import com.tanvoid0.tanspring.models.user.career.Career;
import com.tanvoid0.tanspring.models.user.career.organization.Organization;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.io.Serial;
import java.io.Serializable;

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
@Table(name = "career_volunteer")
public class Volunteer extends Organization implements Serializable {
  public static final String NAME = "Volunteer";

  @Serial
  private static final long serialVersionUID = 3905488673190006102L;

  private String role;

  private Long orderSeq;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "career_id", nullable = false)
  private Career career;

  @PostPersist
  private void postPersist() {
    this.orderSeq = this.id;
  }
}
