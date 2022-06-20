package com.axonactive.personalproject.exception;

import org.springframework.http.HttpStatus;

public class BusinessConstraintException extends Exception {
  private static final String HIRING_MANAGER_DEPARTMENT_MISS_MATCH_MSG_KEY =
      "EmployeeDepartmentInputMissMatch";
  private static final String HIRING_MANAGER_DEPARTMENT_MISS_MATCH_MSG =
      "Hiring Manager Can Not Initial Hiring Request For Other Department.";
  private static final String INVALID_HIRING_MANAGER_MSG_KEY = "InvalidHiringManager";
  private static final String INVALID_HIRING_MANAGER_MSG = "The Employee Is Not A Hiring Manager.";
  private static final String INVALID_HR_OFFICER_MSG_KEY = "InvalidHrOfficer";
  private static final String INVALID_HR_OFFICER_MSG = "The Employee Is Not A Hr Officer.";

  private static final String INVALID_SUBMITTED_DATE_MSG_KEY = "InvalidSubmittedDate";
  private static final String INVALID_SUBMITTED_DATE_MSG =
      "The Summited Date Must Not Be After Today.";

  private static final String INVALID_JOINED_DATE_MSG_KEY = "InvalidJoinedDate";
  private static final String INVALID_JOINED_DATE_MSG = "The Joined Date Must Not Be After Today.";

  private static final String INVALID_RESIGNED_DATE_MSG_KEY = "InvalidResignedDate";
  private static final String INVALID_RESIGNED_DATE_MSG =
      "The Resigned Date Must Not Be Before Joined Date.";

  private static final String INVALID_ONBOARD_DATE_MSG_KEY = "InvalidOnBoardDate";
  private static final String INVALID_ONBOARD_DATE_MSG =
      "The OnBoard Date Must Not Be Before Today.";

  private static final String INVALID_ISSUED_DATE_MSG_KEY = "InvalidIssuedDate";
  private static final String INVALID_ISSUED_DATE_MSG = "The Issued Date Must Not Be Before Today.";

  private static final String INVALID_EXPIRED_DATE_MSG_KEY = "InvalidOnBoardDate";
  private static final String INVALID_EXPIRED_DATE_MSG =
      "The Expired Date Must Not Be After Issued.";

  public static ResponseException notFound(String messageKey, String message) {
    return new ResponseException(messageKey, message, HttpStatus.NOT_FOUND);
  }

  public static ResponseException badRequest(String messageKey, String message) {
    return new ResponseException(messageKey, message, HttpStatus.BAD_REQUEST);
  }

  public static ResponseException internalServerError(String messageKey, String message) {
    return new ResponseException(messageKey, message, HttpStatus.INTERNAL_SERVER_ERROR);
  }

  public static ResponseException invalidHrOfficer() {
    return badRequest(INVALID_HR_OFFICER_MSG_KEY, INVALID_HR_OFFICER_MSG);
  }

  public static ResponseException invalidHiringManagerOfficer() {
    return badRequest(INVALID_HIRING_MANAGER_MSG_KEY, INVALID_HIRING_MANAGER_MSG);
  }

  public static ResponseException hiringManagerDepartmentMissMatch() {
    return badRequest(
        HIRING_MANAGER_DEPARTMENT_MISS_MATCH_MSG_KEY, HIRING_MANAGER_DEPARTMENT_MISS_MATCH_MSG);
  }

  public static ResponseException invalidSubmittedDate() {
    return badRequest(INVALID_JOINED_DATE_MSG_KEY, INVALID_SUBMITTED_DATE_MSG);
  }

  public static ResponseException invalidJoinedDate() {
    return badRequest(INVALID_SUBMITTED_DATE_MSG_KEY, INVALID_JOINED_DATE_MSG);
  }

  public static ResponseException invalidResignedDate() {
    return badRequest(INVALID_RESIGNED_DATE_MSG_KEY, INVALID_RESIGNED_DATE_MSG);
  }

  public static ResponseException invalidOnBoardDate() {
    return badRequest(INVALID_ONBOARD_DATE_MSG_KEY, INVALID_ONBOARD_DATE_MSG);
  }

  public static ResponseException invalidIssuedDate() {
    return badRequest(INVALID_ISSUED_DATE_MSG_KEY, INVALID_ISSUED_DATE_MSG);
  }

  public static ResponseException invalidExpiredDate() {
    return badRequest(INVALID_EXPIRED_DATE_MSG_KEY, INVALID_EXPIRED_DATE_MSG);
  }

}
