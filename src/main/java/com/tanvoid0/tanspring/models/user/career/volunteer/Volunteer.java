package com.tanvoid0.tanspring.models.user.career.volunteer;


import com.tanvoid0.tanspring.models.user.career.organization.Organization;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

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
@Table(name = "career_volunteer")
public class Volunteer extends Organization implements Serializable {
  public static final String NAME = "Volunteer";

  @Serial
  private static final long serialVersionUID = 3905488673190006102L;

  private String role;

  private Long orderSeq;

  @PostPersist
  private void postPersist() {
    this.orderSeq = this.id;
  }
}
