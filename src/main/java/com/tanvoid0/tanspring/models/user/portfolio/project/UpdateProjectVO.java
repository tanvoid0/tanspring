package com.tanvoid0.tanspring.models.user.portfolio.project;

import com.tanvoid0.tanspring.common.vo.BaseVO;
import com.tanvoid0.tanspring.models.user.skill.entity.hard.framework.PlatformType;
import com.tanvoid0.tanspring.models.util_entities.image_link.ImageLinkVO;
import com.tanvoid0.tanspring.models.util_entities.tag.TagVO;

import java.util.ArrayList;
import java.util.List;

import jakarta.validation.constraints.NotNull;

public class UpdateProjectVO extends BaseVO {
  private final List<TagVO> tags = new ArrayList<>();
  private final List<ImageLinkVO> images = new ArrayList<>();
  @NotNull
  private String title;
  private String timeline;
  private String description;
  private String demo;
  private String source;
  private ProjectStatus status = ProjectStatus.UNKNOWN;
  private PlatformType platform = PlatformType.UNCATEGORIZED;
}
