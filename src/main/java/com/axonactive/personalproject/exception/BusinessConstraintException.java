package com.axonactive.personalproject.exception;

import org.springframework.http.HttpStatus;

public class BusinessConstraintException extends Exception{
    private static final String HIRING_MANAGER_DEPARTMENT_MISS_MATCH_MSG_KEY="EmployeeDepartmentInputMissMatch";
    private static final String HIRING_MANAGER_DEPARTMENT_MISS_MATCH_MSG="Hiring Manager Can Not Initial Hiring Request For Other Department.";
    private static final String INVALID_HIRING_MANAGER_MSG_KEY="InvalidHiringManager";
    private static final String INVALID_HIRING_MANAGER_MSG="This Employee Is Not A Hiring Manager.";
    private static final String INVALID_HR_OFFICER_MSG_KEY="InvalidHrOfficer";
    private static final String INVALID_HR_OFFICER_MSG="This Employee Is Not A Hr Officer.";

    private static final String INVALID_SUBMITTED_DATE_MSG_KEY="InvalidSubmittedDate";
    private static final String INVALID_SUBMITTED_DATE_MSG="This Summited Date Must Not Be After Today.";

    private static final String INVALID_JOINED_DATE_MSG_KEY="InvalidJoinedDate";
    private static final String INVALID_JOINED_DATE_MSG="This Joined Date Must Not Be After Today.";

    private static final String INVALID_RESIGNED_DATE_MSG_KEY="InvalidResignedDate";
    private static final String INVALID_RESIGNED_DATE_MSG="This Resigned Date Must Not Be Before Joined Date.";

    private static final String INVALID_ONBOARD_DATE_MSG_KEY="InvalidOnBoardDate";
    private static final String INVALID_ONBOARD_DATE_MSG="This OnBoard Date Must Not Be Before Today.";





    public static ResponseException invalidHrOfficer() {
        return new ResponseException(INVALID_HR_OFFICER_MSG_KEY,INVALID_HR_OFFICER_MSG, HttpStatus.BAD_REQUEST);
    }
    public static ResponseException invalidHiringManagerOfficer() {
        return new ResponseException(INVALID_HIRING_MANAGER_MSG_KEY,INVALID_HIRING_MANAGER_MSG, HttpStatus.BAD_REQUEST);
    }
    public static ResponseException hiringManagerDepartmentMissMatch() {
        return new ResponseException(HIRING_MANAGER_DEPARTMENT_MISS_MATCH_MSG_KEY,HIRING_MANAGER_DEPARTMENT_MISS_MATCH_MSG, HttpStatus.BAD_REQUEST);
    }

    public static  ResponseException invalidSubmittedDate() {
        return new ResponseException(INVALID_JOINED_DATE_MSG_KEY,INVALID_SUBMITTED_DATE_MSG,HttpStatus.BAD_REQUEST);
    }
    public static  ResponseException invalidJoinedDate() {
        return new ResponseException(INVALID_SUBMITTED_DATE_MSG_KEY,INVALID_JOINED_DATE_MSG,HttpStatus.BAD_REQUEST);
    }
    public static  ResponseException invalidResignedDate() {
        return new ResponseException(INVALID_RESIGNED_DATE_MSG_KEY,INVALID_RESIGNED_DATE_MSG,HttpStatus.BAD_REQUEST);
    }
    public static  ResponseException invalidOnBoardDate() {
        return new ResponseException(INVALID_ONBOARD_DATE_MSG_KEY,INVALID_ONBOARD_DATE_MSG,HttpStatus.BAD_REQUEST);
    }


}
