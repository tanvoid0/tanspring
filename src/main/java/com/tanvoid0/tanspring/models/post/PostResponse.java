package com.tanvoid0.tanspring.models.post;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PostResponse {
  private List<PostVO> content;
  private int pageNo;
  private int pageSize;
  private long totalElements;
  private int totalPages;
  private boolean last;
}
