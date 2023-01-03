package com.tanvoid0.tanspring.models.user.portfolio.project;

import com.tanvoid0.tanspring.common.vo.BaseVO;
import com.tanvoid0.tanspring.models.user.skill.entity.hard.framework.PlatformType;
import com.tanvoid0.tanspring.models.util_entities.image_link.ImageLinkVO;
import com.tanvoid0.tanspring.models.util_entities.tag.TagVO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.ArrayList;
import java.util.List;

@Data
@SuperBuilder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
public class ProjectVO extends BaseVO {
  private String title;
  private String timeline;
  private String description;
  private String demo;
  private String source;
  private PlatformType platform = PlatformType.UNCATEGORIZED;
  private List<TagVO> tags = new ArrayList<>();
  private List<ImageLinkVO> images = new ArrayList<>();
  private Long orderSeq;
}
