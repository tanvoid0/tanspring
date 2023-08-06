package com.tanvoid0.tanspring.models.user.hobby;

import com.tanvoid0.tanspring.common.vo.BaseEntity;
import com.tanvoid0.tanspring.models.user.AppUser;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import jakarta.persistence.*;

@Getter
@Setter
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "user_hobbies", uniqueConstraints = {
    @UniqueConstraint(columnNames = {"title"})
})
@org.hibernate.annotations.NamedQuery(name = "Hobby.findAll", query = "select h from Hobby h order by h.orderSeq")
public class Hobby extends BaseEntity {
  @Column(nullable = false)
  private String title;

  private String icon;

  private String image;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "user_id", nullable = false)
  private AppUser user;

}
