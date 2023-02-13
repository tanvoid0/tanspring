package com.tanvoid0.tanspring.config;

import org.springframework.util.StreamUtils;

import java.io.IOException;
import java.io.InputStream;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletRequestWrapper;

public class CachedHttpServletRequest extends HttpServletRequestWrapper {
  private byte[] cachedPayload;

  public CachedHttpServletRequest(final HttpServletRequest request) throws IOException {
    super(request);
    final InputStream requestInputStream = request.getInputStream();
    this.cachedPayload = StreamUtils.copyToByteArray(requestInputStream);
  }
}
