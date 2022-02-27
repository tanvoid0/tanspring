package com.tanvoid0.tanspring.core.diff;

public interface DifferenceMapperProperty<T> {
  DifferenceMapperProperty<T> comparisonStrategy(DiffComparator<T> diffComparator);

  DifferenceMapperProperty<T> reportMapper(DiffReportMapper<T> reportMapper);
}
