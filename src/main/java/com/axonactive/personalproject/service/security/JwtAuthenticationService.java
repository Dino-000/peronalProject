package com.axonactive.personalproject.service.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.axonactive.personalproject.exception.ResourceNotFoundException;
import com.axonactive.personalproject.exception.UnauthorizedAccessException;
import com.axonactive.personalproject.service.UserAccountService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;

@Slf4j
@Service
@RequiredArgsConstructor
public class JwtAuthenticationService {
  @Autowired UserAccountService userAccountService;

  // check password from database
  private UserAccount checkValidUserAccount(UserAccount user)
      throws UnauthorizedAccessException, ResourceNotFoundException {
    //    if (!("superAdmin".equalsIgnoreCase(user.getUserName()) &&
    // user.getPassWord().equals("1234"))) {
    if (userAccountService.findByUserName(user.getUserName()) == null
        || userAccountService
            .findByUserName(user.getUserName())
            .getPassWord()
            .equals(user.getPassWord())) {
      throw new UnauthorizedAccessException("Unauthorized user");
    }
    return user;
  }

  public Token createToKen(UserAccount user)
      throws UnauthorizedAccessException, ResourceNotFoundException {
    this.checkValidUserAccount(user);
    String token = null;
    String secretKey = "this is secret";
    String issuer = "Marvel";
    int tokenDuration = 690_000_000;
    try {
      Algorithm algorithm = Algorithm.HMAC512(secretKey);
      token =
          JWT.create()
              .withIssuer(issuer)
              .withClaim("username", user.getUserName())
              .withExpiresAt(setTokenDuration(tokenDuration))
              .sign(algorithm);
    } catch (IllegalArgumentException e) {
      log.info(e.getMessage());
    }
    if (token == null) {
      throw new UnauthorizedAccessException("Unauthorized");
    }
    return new Token(token, tokenDuration);
  }

  public void checkAuthorizedToken(String token) throws UnauthorizedAccessException {
    log.info("Input token is: {}", token);
    if (token == null) {
      throw new UnauthorizedAccessException("Unauthorized");
    }
    try {
      String secretKey = "this is secret";
      Algorithm algorithm = Algorithm.HMAC512(secretKey);
      JWTVerifier verifier = JWT.require(algorithm).build();
      DecodedJWT jwt = verifier.verify(token);
    } catch (JWTVerificationException e) {
      log.info(e.getMessage());
      throw new UnauthorizedAccessException("Unauthorized");
    } catch (IllegalArgumentException e) {
      log.info(e.getMessage());
    }
  }

  private Date setTokenDuration(int minutes) {
    Calendar calendar = Calendar.getInstance();
    calendar.add(Calendar.MINUTE, minutes);
    return calendar.getTime();
  }
}
