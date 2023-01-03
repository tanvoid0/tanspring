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

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PostPersist;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

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

  private Long orderSeq;

  @PostPersist
  private void postPersist() {
    this.orderSeq = this.id;
  }
}
