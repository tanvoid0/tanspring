package com.tanvoid0.tanspring.core.diff;

public interface DifferenceMapperType<T> {
  DifferenceMapperType<T> comparisonStrategy(DiffComparator<T> diffComparator);

  DifferenceMapperType<T> reportMapper(DiffReportMapper<T> reportMapper);
}
