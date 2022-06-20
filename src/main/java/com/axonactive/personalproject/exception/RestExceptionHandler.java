//package com.axonactive.personalproject.exception;
//
//import org.springframework.http.HttpHeaders;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.ExceptionHandler;
//import org.springframework.web.context.request.WebRequest;
//import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
//
//import javax.validation.ConstraintViolationException;
//
//public class RestExceptionHandler extends ResponseEntityExceptionHandler {
//  @ExceptionHandler({ResponseException.class})
//  public ResponseEntity<Object> handleAll(final ResponseException e, final WebRequest request) {
//    logger.error("error", e);
//    return new ResponseEntity<>(
//        e.getResponseBody(), new HttpHeaders(), e.getResponseBody().getHttpStatus());
//  }
//
//  @ExceptionHandler(ConstraintViolationException.class)
//  protected ResponseEntity<Object> handleConstraintViolation(
//      ConstraintViolationException e, WebRequest request) {
//    return handleExceptionInternal(
//        e, e.getConstraintViolations(), new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
//  }
//}
