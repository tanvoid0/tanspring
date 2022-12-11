package com.tanvoid0.tanspring.models.util_entities.image_link;

import com.tanvoid0.tanspring.common.vo.BaseVO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class ImageLinkVO extends BaseVO {
  private String title;
  private String description;
  private String url;
}