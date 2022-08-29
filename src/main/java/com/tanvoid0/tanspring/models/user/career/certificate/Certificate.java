package com.tanvoid0.tanspring.models.user.career.certificate;

import com.tanvoid0.tanspring.models.user.career.Career;
import com.tanvoid0.tanspring.models.user.career.organization.Organization;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import java.io.Serial;
import java.io.Serializable;

@Getter
@Setter
@SuperBuilder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "career_certificate")
public class Certificate extends Organization implements Serializable {

  public static final String NAME = "Certificate";

  @Serial
  private static final long serialVersionUID = 6262234836769761123L;

  private String graduation;

  private String url;

  private String certificate;

  private Long orderSeq;

  @PostPersist
  private void postPersist() {
    this.orderSeq = this.id;
  }
}