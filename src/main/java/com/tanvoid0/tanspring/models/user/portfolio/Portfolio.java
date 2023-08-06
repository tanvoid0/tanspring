package com.tanvoid0.tanspring.models.user.portfolio;

import com.tanvoid0.tanspring.common.vo.BaseEntity;
import com.tanvoid0.tanspring.models.user.AppUser;
import com.tanvoid0.tanspring.models.user.portfolio.oj.OnlineJudge;
import com.tanvoid0.tanspring.models.user.portfolio.project.Project;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.io.Serial;
import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.OrderBy;
import jakarta.persistence.Table;

@Getter
@Setter
@SuperBuilder(toBuilder = true)
@AllArgsConstructor
@Entity
@Table(name = "portfolios")
public class Portfolio extends BaseEntity {
  @Serial
  private static final long serialVersionUID = 5789856826031749553L;

  @OneToMany(mappedBy = "portfolio", cascade = CascadeType.ALL, orphanRemoval = false)
  @OrderBy("orderSeq ASC")
  private Set<OnlineJudge> onlineJudges;

  @OneToMany(mappedBy = "portfolio", cascade = CascadeType.ALL, orphanRemoval = false)
  @OrderBy("orderSeq ASC")
  private Set<Project> projects;

  @OneToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "user_id", nullable = false)
  private AppUser user;

  public Portfolio() {
    this.onlineJudges = new HashSet<>();
    this.projects = new HashSet<>();
  }
}
