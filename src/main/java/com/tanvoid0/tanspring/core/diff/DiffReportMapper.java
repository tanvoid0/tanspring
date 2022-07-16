package com.tanvoid0.tanspring.core.diff;

public interface DiffReportMapper<T> {
  String reportValue(final T valueSource);
}
