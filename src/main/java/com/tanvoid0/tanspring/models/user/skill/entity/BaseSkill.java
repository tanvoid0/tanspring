package com.tanvoid0.tanspring.models.user.skill.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.io.Serial;
import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
@SuperBuilder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
public abstract class BaseSkill implements Serializable {

  @Serial
  private static final long serialVersionUID = -5515796402037749601L;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  protected long id;

  @Version
  protected long version;

  @Temporal(TemporalType.TIMESTAMP)
  @CreationTimestamp
  @Column(nullable = false)
  protected Date createdAt;

  @Temporal(TemporalType.TIMESTAMP)
  @UpdateTimestamp
  @Column(nullable = false)
  protected Date updatedAt;

  @Column(nullable = false, unique = true)
  private String title;

  private String icon;
  private String image;

  private String fluency;
  private float fluencyVal;

  private String description;
}
