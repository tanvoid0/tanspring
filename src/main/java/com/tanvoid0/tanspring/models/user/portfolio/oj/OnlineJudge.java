package com.tanvoid0.tanspring.models.user.portfolio.oj;

import com.tanvoid0.tanspring.common.vo.BaseEntity;
import com.tanvoid0.tanspring.models.user.portfolio.Portfolio;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.io.Serial;
import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;

@Getter
@Setter
@SuperBuilder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "portfolio_online_judge", uniqueConstraints = {
    @UniqueConstraint(columnNames = {"name"})
})
public class OnlineJudge extends BaseEntity implements Serializable {
  @Serial
  private static final long serialVersionUID = 7517821427595316551L;

  @Column(nullable = false)
  private String name;

  private String icon;

  private String image;

  @Column(nullable = false)
  private String progress;

  @Column(nullable = false)
  private String url;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "portfolio_id", nullable = false)
  private Portfolio portfolio;

}
