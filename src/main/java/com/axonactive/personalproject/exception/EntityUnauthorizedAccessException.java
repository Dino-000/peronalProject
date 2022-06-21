package com.axonactive.personalproject.exception;

public class EntityUnauthorizedAccessException extends Exception {
  private final String errorKey;

  public EntityUnauthorizedAccessException(String errorKey) {
    super();
    this.errorKey = errorKey;
  }

  public EntityUnauthorizedAccessException(String message, Throwable cause, String errorKey) {
    super(message, cause);
    this.errorKey = errorKey;
  }

  public EntityUnauthorizedAccessException(String message, String errorKey) {
    super(message);
    this.errorKey = errorKey;
  }

  public EntityUnauthorizedAccessException(Throwable cause, String errorKey) {
    super(cause);
    this.errorKey = errorKey;
  }
}
