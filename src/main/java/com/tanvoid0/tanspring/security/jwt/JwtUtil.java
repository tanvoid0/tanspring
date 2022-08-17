package com.tanvoid0.tanspring.security.jwt;

import com.tanvoid0.tanspring.common.exception.BlogAPIException;
import io.jsonwebtoken.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.security.SecureRandom;
import java.util.Base64;

public class JwtUtil {
    @Value("${app.jwt-secret}")
    private static String jwtSecret;
    @Value("${app.jwt-expiration-milliseconds}")
    private static int jwtExpirationInMs;

    public static void main(String[] args) {
        System.out.println(generateSafeToken());
    }

    private static String generateSafeToken() {
        SecureRandom random = new SecureRandom();
        byte[] bytes = new byte[36]; // 36 bytes * 8 = 288 bits, a little bit more than
        // the 256 required bits
        random.nextBytes(bytes);
        var encoder = Base64.getUrlEncoder().withoutPadding();
        return encoder.encodeToString(bytes);
    }

    public static String getUsernameFromHttpRequest(HttpServletRequest request) {
        // get JWT (token) from http request
        String token = getJWTTokenFromRequest(request);
        // validate token
        if (StringUtils.hasText(token) && validateToken(token)) {
            // get username from token
            return getUsernameFromJWT(token);
        }
        return null;
    }

    public static String getJWTTokenFromRequest(HttpServletRequest request) {
        String bearerToken = request.getHeader("Authorization");
        if (StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring(7, bearerToken.length());
        }
        return null;
    }

    public static boolean validateToken(String token) {
        try {
            Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token);
            return true;
        } catch (SignatureException ex) {
            throw new BlogAPIException(HttpStatus.BAD_REQUEST, "Invalid JWT signature");
        } catch (MalformedJwtException ex) {
            throw new BlogAPIException(HttpStatus.BAD_REQUEST, "Invalid JWT token");
        } catch (ExpiredJwtException ex) {
            throw new BlogAPIException(HttpStatus.BAD_REQUEST, "Expired JWT token");
        } catch (UnsupportedJwtException ex) {
            throw new BlogAPIException(HttpStatus.BAD_REQUEST, "Unsupported JWT token");
        } catch (IllegalArgumentException ex) {
            throw new BlogAPIException(HttpStatus.BAD_REQUEST, "JWT claims string is empty.");
        }
    }

    public static String getUsernameFromJWT(String token) {
        Claims claims = Jwts.parser()
                .setSigningKey(jwtSecret)
                .parseClaimsJws(token)
                .getBody();
        return claims.getSubject();
    }
}
