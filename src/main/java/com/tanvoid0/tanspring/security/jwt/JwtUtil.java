package com.tanvoid0.tanspring.security.jwt;

import com.tanvoid0.tanspring.common.exception.jwt.JWTException;
import com.tanvoid0.tanspring.common.exception.jwt.JWTExceptionType;

import org.springframework.util.StringUtils;

import java.security.SecureRandom;
import java.util.Base64;

import javax.servlet.http.HttpServletRequest;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;

public class JwtUtil {

  public static void main(String[] args) {
    System.out.println(generateSafeToken());
  }

  private static String generateSafeToken() {
    SecureRandom random = new SecureRandom();
    byte[] bytes = new byte[36]; // 36 bytes * 8 = 288 bits, a little more than
    // the 256 required bits
    random.nextBytes(bytes);
    var encoder = Base64.getUrlEncoder().withoutPadding();
    return encoder.encodeToString(bytes);
  }

  public static String getJWTTokenFromRequest(HttpServletRequest request) {
    String bearerToken = request.getHeader("Authorization");
    if (StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer ")) {
      return bearerToken.substring(7, bearerToken.length());
    }
    return null;
  }

  public static boolean validateToken(final String jwtSecret, final String token) {
    try {
      Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token);
      return true;
    } catch (SignatureException ex) {
      throw new JWTException(JWTExceptionType.INVALID);
    } catch (MalformedJwtException ex) {
      throw new JWTException(JWTExceptionType.INVALID_TOKEN);
    } catch (ExpiredJwtException ex) {
      throw new JWTException(JWTExceptionType.EXPIRED);
    } catch (UnsupportedJwtException ex) {
      throw new JWTException(JWTExceptionType.UNSUPPORTED);
    } catch (IllegalArgumentException ex) {
      throw new JWTException(JWTExceptionType.MISSING);
    }
  }

  public static String getUsernameFromJWT(final String jwtSecret, String token) {
    Claims claims = Jwts.parser()
        .setSigningKey(jwtSecret)
        .parseClaimsJws(token)
        .getBody();
    return claims.getSubject();
  }

  public static String getJWTFromRequest(HttpServletRequest request) {
    String bearerToken = request.getHeader("Authorization");
    if (!StringUtils.hasText(bearerToken)) {
      throw new JWTException(JWTExceptionType.MISSING);
    } else if (!bearerToken.startsWith("Bearer ")) {
      throw new JWTException(JWTExceptionType.INVALID);
    }
    return bearerToken.substring(7);
  }
}
