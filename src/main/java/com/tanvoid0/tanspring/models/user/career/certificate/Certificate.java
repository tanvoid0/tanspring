package com.tanvoid0.tanspring.models.user.career.certificate;

import com.tanvoid0.tanspring.models.user.career.Career;
import com.tanvoid0.tanspring.models.user.career.organization.Organization;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.io.Serial;
import java.io.Serializable;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

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


  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "career_id", nullable = false)
  private Career career;

}
