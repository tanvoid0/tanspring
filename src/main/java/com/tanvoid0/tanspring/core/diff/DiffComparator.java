package com.tanvoid0.tanspring.core.diff;

public interface DiffComparator<T> {
  DiffState compare(final T working, final T base);
}
