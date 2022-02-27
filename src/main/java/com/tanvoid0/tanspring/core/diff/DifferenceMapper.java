package com.tanvoid0.tanspring.core.diff;

import java.util.List;
import java.util.Set;

public interface DifferenceMapper {
  <T> DifferenceMapperType<T> forType(Class<T> type);

  <T> DifferenceMapperProperty<T> forProperty(String property, Class<T> returnClass);

  <T> DifferenceMapperProperty<List<T>> forListProperty(String property, Class<T> returnClass);

  <T> DifferenceMapperProperty<Set<T>> forSetProperty(String property, Class<T> returnClass);

  void ignoreProperty(String property);
}
