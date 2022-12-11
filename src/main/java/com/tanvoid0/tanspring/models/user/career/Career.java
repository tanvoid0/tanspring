package com.tanvoid0.tanspring.models.user.career;

import com.tanvoid0.tanspring.common.vo.BaseEntity;
import com.tanvoid0.tanspring.models.user.User;
import com.tanvoid0.tanspring.models.user.career.academic.Academic;
import com.tanvoid0.tanspring.models.user.career.achievement.Achievement;
import com.tanvoid0.tanspring.models.user.career.certificate.Certificate;
import com.tanvoid0.tanspring.models.user.career.experience.Experience;
import com.tanvoid0.tanspring.models.user.career.volunteer.Volunteer;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.io.Serial;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Getter
@Setter
@SuperBuilder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "user_career")
public class Career extends BaseEntity implements Serializable {

  @Serial
  private static final long serialVersionUID = 4294294701060916909L;

  @OneToMany(mappedBy = "career", cascade = CascadeType.ALL, orphanRemoval = true)
  private Set<Academic> academics = new HashSet<>();

  @OneToMany(mappedBy = "career", cascade = CascadeType.ALL, orphanRemoval = true)
  private Set<Achievement> achievements = new HashSet<>();

  @OneToMany(mappedBy = "career", cascade = CascadeType.ALL, orphanRemoval = true)
  private Set<Certificate> certificates = new HashSet<>();

  @OneToMany(mappedBy = "career", cascade = CascadeType.ALL, orphanRemoval = true)
  private Set<Experience> experiences = new HashSet<>();

  @OneToMany(mappedBy = "career", cascade = CascadeType.ALL, orphanRemoval = true)
  private Set<Volunteer> volunteers = new HashSet<>();

  @OneToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "user_id", nullable = false)
  private User user;
}
