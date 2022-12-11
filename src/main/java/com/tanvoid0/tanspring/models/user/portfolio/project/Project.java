package com.tanvoid0.tanspring.models.user.portfolio.project;

import com.tanvoid0.tanspring.common.vo.BaseEntity;
import com.tanvoid0.tanspring.models.user.portfolio.Portfolio;
import com.tanvoid0.tanspring.models.user.skill.entity.hard.framework.PlatformType;
import com.tanvoid0.tanspring.models.util_entities.image_link.ImageLink;
import com.tanvoid0.tanspring.models.util_entities.tag.Tag;

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
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.PostPersist;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Getter
@Setter
@SuperBuilder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "portfolio_project", uniqueConstraints = {
    @UniqueConstraint(columnNames = {"title"})
})
public class Project extends BaseEntity implements Serializable {
  @Serial
  private static final long serialVersionUID = 3476482657535701793L;

  @Column(nullable = false, unique = true)
  private String title;

  private String timeline;

  @Column(length = 3000)
  private String description;

  private String demo;
  private String source;

  private PlatformType platform = PlatformType.UNCATEGORIZED;

  @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
  @JoinTable(name = "project_tags",
      joinColumns = @JoinColumn(name = "project_id", referencedColumnName = "id"),
      inverseJoinColumns = @JoinColumn(name = "tag_id", referencedColumnName = "id")
  )
  private Set<Tag> tags = new HashSet<>();


  @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
  @JoinTable(name = "project_images",
      joinColumns = @JoinColumn(name = "project_id", referencedColumnName = "id"),
      inverseJoinColumns = @JoinColumn(name = "image_id", referencedColumnName = "id")
  )
  private Set<ImageLink> images = new HashSet<>();

  @Enumerated(EnumType.STRING)
  private ProjectStatus status;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "portfolio_id", nullable = false)
  private Portfolio portfolio;

  private Long orderSec;

  @PostPersist
  private void postPersist() {
    this.orderSec = this.id;
  }
}
