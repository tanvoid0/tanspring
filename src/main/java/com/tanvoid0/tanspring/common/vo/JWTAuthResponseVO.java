package com.tanvoid0.tanspring.common.vo;

public class JWTAuthResponseVO {
  private String accessToken;
  private String tokenType = "Bearer";

  public JWTAuthResponseVO(String accessToken) {
    this.accessToken = accessToken;
  }

  public void setAccessToken(String accessToken) {
    this.accessToken = accessToken;
  }

  public void setTokenType(String tokenType) {
    this.tokenType = tokenType;
  }

  public String getAccessToken() {
    return accessToken;
  }

  public String getTokenType() {
    return tokenType;
  }
}
