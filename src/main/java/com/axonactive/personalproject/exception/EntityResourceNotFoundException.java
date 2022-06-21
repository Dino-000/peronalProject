package com.axonactive.personalproject.exception;

public class EntityResourceNotFoundException extends Exception {
  private final String errorKey;

  public EntityResourceNotFoundException(String errorKey) {
    super();
    this.errorKey = errorKey;
  }

  public EntityResourceNotFoundException(String message, Throwable cause, String errorKey) {
    super(message, cause);
    this.errorKey = errorKey;
  }

  public EntityResourceNotFoundException(String message, String errorKey) {
    super(message);
    this.errorKey = errorKey;
  }

  public EntityResourceNotFoundException(Throwable cause, String errorKey) {
    super(cause);
    this.errorKey = errorKey;
  }
}
