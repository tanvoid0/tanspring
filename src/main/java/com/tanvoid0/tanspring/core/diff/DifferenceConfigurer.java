package com.tanvoid0.tanspring.core.diff;

import com.tanvoid0.tanspring.core.vo.BaseVO;

public interface DifferenceConfigurer<T extends BaseVO> {
  void configure(DifferenceMapper mapper);

  DiffReportBuilder<T> getDiffReportBuilder(Class<T> returnClass);
}
