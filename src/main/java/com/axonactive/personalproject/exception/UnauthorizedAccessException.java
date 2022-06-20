package com.axonactive.personalproject.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class UnauthorizedAccessException extends Exception {
  private static final String FORBIDDEN_MSG_KEY = "UnauthorizedAccess";
  private static final String FORBIDDEN_MSG = "You don’t have permission to access this resources";

  private static final String UNAUTHORIZED_MSG_KEY = "UnauthenticatedUser";
  private static final String UNAUTHORIZED_MSG = "Wrong password provided";

  public static ResponseException unauthorized(String messageKey, String message) {
    return new ResponseException(messageKey, message, HttpStatus.UNAUTHORIZED);
  }

  public static ResponseException unauthorized() {
    return new ResponseException(UNAUTHORIZED_MSG_KEY, UNAUTHORIZED_MSG, HttpStatus.UNAUTHORIZED);
  }

  public static ResponseException forbidden(String messageKey, String message) {
    return new ResponseException(messageKey, message, HttpStatus.FORBIDDEN);
  }

  public static ResponseException forbidden() {
    return new ResponseException(FORBIDDEN_MSG_KEY, FORBIDDEN_MSG, HttpStatus.FORBIDDEN);
  }
}
