package com.tanvoid0.tanspring.common.vo;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.PostPersist;
import jakarta.persistence.Version;

@Getter
@Setter
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
@SuperBuilder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
public abstract class BaseEntity implements Serializable {
  @Serial
  private static final long serialVersionUID = -1076009334144334204L;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  protected long id;

  @Version
  protected long version;

  @CreationTimestamp
  @Column(nullable = false)
  protected LocalDateTime createdAt;

  @UpdateTimestamp
  @Column(nullable = false)
  protected LocalDateTime updatedAt;

  protected Long orderSeq;

  @PostPersist
  protected void postPersist() {
    this.orderSeq = this.id;
  }

}
