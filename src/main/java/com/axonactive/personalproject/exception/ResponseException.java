package com.axonactive.personalproject.exception;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@NoArgsConstructor
public class ResponseException extends RuntimeException {

  @Getter private ResponseBody responseBody;

  public ResponseException(String messageKey, String message, HttpStatus httpStatus) {
    this.responseBody = new ResponseBody(messageKey, message, httpStatus);
  }

  @Getter
  public static class ResponseBody {
    private String messageKey;
    private String message;
    private HttpStatus httpStatus;

    public ResponseBody(String messageKey, String message, HttpStatus httpStatus) {
      this.messageKey = messageKey;
      this.message = message;
      this.httpStatus = httpStatus;
    }
  }
}
