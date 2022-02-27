package com.tanvoid0.tanspring.core.diff;

import com.tanvoid0.tanspring.core.vo.BaseVO;

public interface DiffReportBuilder<T extends BaseVO> {
  DiffReportVO build(T previousValue, T newValue);
}
