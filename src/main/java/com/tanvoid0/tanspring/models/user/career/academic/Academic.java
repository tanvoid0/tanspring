package com.tanvoid0.tanspring.models.user.career.academic;

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
@Table(name = "career_academic")
public class Academic extends Organization implements Serializable {

  public static final String NAME = "Academic";

  @Serial
  private static final long serialVersionUID = 3662133158529326555L;

  @Column(nullable = false)
  private String graduation;

  private Long orderSeq;

  @PostPersist
  private void postPersist() {
    this.orderSeq = this.id;
  }
}
