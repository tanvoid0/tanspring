package com.tanvoid0.tanspring.core.diff;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DiffReportElementVO {
  private String property;

  private DiffState change;

  private String previousValue;

  private String newValue;
}
