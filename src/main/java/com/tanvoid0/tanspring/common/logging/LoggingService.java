package com.tanvoid0.tanspring.common.logging;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public interface LoggingService {

  void displayReq(HttpServletRequest request, Object body);

  void displayResp(HttpServletRequest request, HttpServletResponse response, Object body);
}
