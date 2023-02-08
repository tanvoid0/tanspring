package com.tanvoid0.tanspring.security.jwt;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.util.Date;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JwtTokenProvider {

  @Value("${app.jwt-secret}")
  private String jwtSecret;
  @Value("${app.jwt-expiration-milliseconds}")
  private int jwtExpirationInMs;

  // generate token
  public String generateToken(Authentication authentication) {
    String username = authentication.getName();
    Date currentDate = new Date();
    Date expireDate = new Date(currentDate.getTime() + jwtExpirationInMs);

    String token = Jwts.builder()
        .setSubject(username)
        .setIssuedAt(new Date())
        .setExpiration(expireDate)
        .signWith(SignatureAlgorithm.HS512, jwtSecret)
        .compact();
    return token;
  }

  // get username from the token
  public String getUsernameFromJWT(String token) {
    return JwtUtil.getUsernameFromJWT(jwtSecret, token);
  }

  // validate JWT token
  public boolean validateToken(String token) {
    return JwtUtil.validateToken(jwtSecret, token);
  }

}
