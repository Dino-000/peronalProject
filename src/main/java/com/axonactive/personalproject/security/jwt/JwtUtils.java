package com.axonactive.personalproject.security.jwt;

import com.axonactive.personalproject.security.service.impl.UserDetailsImpl;
import io.jsonwebtoken.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.Date;

@Component
public class JwtUtils implements Serializable {
  private static final Logger logger = LoggerFactory.getLogger(JwtUtils.class);
  public static final long JWT_TOKEN_VALIDITY = 30 * 60 * 1000;

  @Value("${jwt.secret}")
  private String jwtSecret;

  public String generateJwtToken(Authentication authentication) {
    UserDetailsImpl userPrincipal = (UserDetailsImpl) authentication.getPrincipal();

    return Jwts.builder()
        .setSubject(userPrincipal.getUsername())
        .setIssuedAt(new Date())
        .setExpiration(new Date(System.currentTimeMillis() + JWT_TOKEN_VALIDITY))
        .signWith(SignatureAlgorithm.HS512, jwtSecret)
        .compact();
  }

  public String getUserNameFromJwtToken(String token) {
    return Jwts.parser().setSigningKey(jwtSecret).parseClaimsJwt(token).getBody().getSubject();
  }

  public boolean validateJwtToken(String authToken) {
    try {
      Jwts.parser().setSigningKey(jwtSecret).parseClaimsJwt(authToken);
      return true;
    } catch (SignatureException e) {
      logger.error("Invalid JWT signature: {}", e.getMessage());
    } catch (MalformedJwtException e) {
      logger.error("Invalid JWT token: {}", e.getMessage());
    } catch (ExpiredJwtException e) {
      logger.error("JWT token is expired: {}", e.getMessage());
    } catch (UnsupportedJwtException e) {
      logger.error("JWT token is unsupported: {}", e.getMessage());
    } catch (IllegalArgumentException e) {
      logger.error("JWT claims string is empty: {}", e.getMessage());
    }
    return false;
  }

    /*
     * //while creating the token - //1. Define claims of the token, like Issuer,
     * Expiration, Subject, and the ID //2. Sign the JWT using the HS512 algorithm
     * and secret key. //3. According to JWS Compact
     * Serialization(https://tools.ietf.org/html/draft-ietf-jose-json-web-signature-
     * 41#section-3.1) // compaction of the JWT to a URL-safe string private String
     * doGenerateToken(Map<String, Object> claims, String subject) {
     *
     * return Jwts.builder() .setClaims(claims) .setSubject(subject)
     * .setIssuedAt(new Date(System.currentTimeMillis())) .setExpiration(new
     * Date(System.currentTimeMillis() + JWT_TOKEN_VALIDITY * 1000))
     * .signWith(SignatureAlgorithm.HS512, secret) .compact(); }
     */
}
