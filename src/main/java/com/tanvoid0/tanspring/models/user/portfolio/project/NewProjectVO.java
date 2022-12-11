package com.tanvoid0.tanspring.models.user.portfolio.project;

import com.tanvoid0.tanspring.models.user.skill.entity.hard.framework.PlatformType;
import com.tanvoid0.tanspring.models.util_entities.image_link.NewImageLinkVO;
import com.tanvoid0.tanspring.models.util_entities.tag.NewTagVO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.HashSet;
import java.util.Set;

@Data
@SuperBuilder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
public class NewProjectVO {
  private String title;
  private String timeline;
  private String description;
  private String demo;
  private String source;
  private ProjectStatus status = ProjectStatus.UNKNOWN;
  private PlatformType platform = PlatformType.UNCATEGORIZED;
  private final Set<NewTagVO> tags = new HashSet<>();
  private final Set<NewImageLinkVO> images = new HashSet<>();
}
