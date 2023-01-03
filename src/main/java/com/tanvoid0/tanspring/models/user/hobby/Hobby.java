package com.tanvoid0.tanspring.models.user.hobby;

import com.tanvoid0.tanspring.common.vo.BaseEntity;
import com.tanvoid0.tanspring.models.user.User;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;

@Getter
@Setter
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "user_hobbies", uniqueConstraints = {
    @UniqueConstraint(columnNames = {"title"})
})
public class Hobby extends BaseEntity {
  @Column(nullable = false)
  private String title;

  private String icon;

  private String image;

  private Long orderSeq;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "user_id", nullable = false)
  private User user;

  @PostPersist
  private void postPersist() {
    this.orderSeq = this.id;
  }
}
