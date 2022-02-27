package com.tanvoid0.tanspring.core.diff;

import lombok.AccessLevel;
import lombok.Setter;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.ArrayList;
import java.util.List;

public class DiffReportVO {
  @Setter(AccessLevel.NONE)
  private List<DiffReportElementVO> elements = new ArrayList<>();

  @JsonIgnore
  public void addReportElement(final DiffReportElementVO reportElement) {
    this.elements.add(reportElement);
  }
}
