package com.axonactive.personalproject.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class ResourceNotFoundException extends  Exception{

    private static final String APPLICATION_FORM_NOT_FOUND_MSG_KEY = "ApplicationFormNotExisted";
    private static final String APPLICATION_FORM_NOT_FOUND_MSG = "Can't not find Application Form with that id.";
    private static final String CANDIDATE_NOT_FOUND_MSG_KEY = "CandidateNotExisted";
    private static final String CANDIDATE_NOT_FOUND_MSG = "Can Not Find Candidate With That Id";
    private static final String CANDIDATE_EDUCATION_NOT_FOUND_MSG_KEY = "CandidateEducationNotExisted";
    private static final String CANDIDATE_EDUCATION_NOT_FOUND_MSG = "Can Not Find Candidate Education With That Id";
    private static final String CANDIDATE_CERTIFICATION_NOT_FOUND_MSG_KEY = "CandidateCertificationNotExisted";
    private static final String CANDIDATE_CERTIFICATION_NOT_FOUND_MSG = "Can Not Find Candidate Certification With That Id";
    private static final String CANDIDATE_SKILL_SET_NOT_FOUND_MSG_KEY = "CandidateSkillSetNotExisted";
    private static final String CANDIDATE_SKILL_SET_NOT_FOUND_MSG = "Can Not Find Candidate SkillSet With That Id";
    private static final String CERTIFICATION__NOT_FOUND_MSG_KEY = "CertificationNotExisted";
    private static final String CERTIFICATION_NOT_FOUND_MSG = "Can Not Find Certification With That Id";
    private static final String DEPARTMENT_NOT_FOUND_MSG_KEY = "DepartmentNotExisted";
    private static final String DEPARTMENT_NOT_FOUND_MSG = "Can Not Find Department With That Id";
    private static final String EDUCATION_NOT_FOUND_MSG_KEY = "EducationNotExisted";
    private static final String EDUCATION_NOT_FOUND_MSG = "Can Not Find Education With That Id";
    private static final String EMPLOYEE_NOT_FOUND_MSG_KEY = "EmployeeNotExisted";
    private static final String EMPLOYEE_NOT_FOUND_MSG = "Can Not Find Employee With That Id";
    private static final String HIRING_REQUEST_NOT_FOUND_MSG_KEY = "HiringRequestNotExisted";
    private static final String HIRING_REQUEST_NOT_FOUND_MSG = "Can Not Find Hiring Request With That Id";
    private static final String HIRING_REQUEST_SKILL_SET_NOT_FOUND_MSG_KEY = "HiringRequestSkillSetNotExisted";
    private static final String HIRING_REQUEST_SKILL_SET_NOT_FOUND_MSG = "Can Not Find Hiring Request Skill Set With That Id";
    private static final String RECRUITMENT_CHANEL_NOT_FOUND_MSG_KEY = "RecruitmentChannelNotExisted";
    private static final String RECRUITMENT_CHANEL_NOT_FOUND_MSG = "Can Not Find Recruitment Channel With That Id";
    private static final String SKILL_SET_NOT_FOUND_MSG_KEY = "SkillSetNotExisted";
    private static final String SKILL_SET_NOT_FOUND_MSG = "Can Not Find Skill Set With That Id";
    private static final String USER_ACCOUNT_NOT_FOUND_MSG_KEY = "AccountNotExisted";
    private static final String USER_ACCOUNT_NOT_FOUND_MSG = "Can Not Find Account With That Id";
    private static final String WORKING_RECORD_NOT_FOUND_MSG_KEY = "WorkingHistoryRecordNotExisted";
    private static final String WORKING_RECORD_NOT_FOUND_MSG = "Can Not Find Working History Record With That Id";
    private static final String WORKING_RECORD_SKILL_SET_NOT_FOUND_MSG_KEY = "WorkingHistoryRecordSkillSetNotExisted";
    private static final String WORKING_RECORD_SKILL_SET_NOT_FOUND_MSG = "Can Not Find Working History Record Skill Set With That Id";


    public static ResponseException applicationFormNotFound() {
        return new ResponseException(APPLICATION_FORM_NOT_FOUND_MSG_KEY,APPLICATION_FORM_NOT_FOUND_MSG, HttpStatus.NOT_FOUND);
    }
    public static ResponseException candidateNotFound() {
        return new ResponseException(CANDIDATE_NOT_FOUND_MSG_KEY,CANDIDATE_NOT_FOUND_MSG, HttpStatus.NOT_FOUND);
    }
    public static ResponseException candidateEducationNotFound() {
        return new ResponseException(CANDIDATE_EDUCATION_NOT_FOUND_MSG_KEY,CANDIDATE_EDUCATION_NOT_FOUND_MSG, HttpStatus.NOT_FOUND);
    }
    public static ResponseException candidateCertificationNotFound() {
        return new ResponseException(CANDIDATE_CERTIFICATION_NOT_FOUND_MSG_KEY,CANDIDATE_CERTIFICATION_NOT_FOUND_MSG, HttpStatus.NOT_FOUND);
    }
    public static ResponseException candidateSkillSetNotFound() {
        return new ResponseException(CANDIDATE_SKILL_SET_NOT_FOUND_MSG_KEY,CANDIDATE_SKILL_SET_NOT_FOUND_MSG, HttpStatus.NOT_FOUND);
    }
    public static ResponseException certificationNotFound() {
        return new ResponseException(CERTIFICATION__NOT_FOUND_MSG_KEY,CERTIFICATION_NOT_FOUND_MSG, HttpStatus.NOT_FOUND);
    }
    public static ResponseException departmentNotFound() {
        return new ResponseException(DEPARTMENT_NOT_FOUND_MSG_KEY,DEPARTMENT_NOT_FOUND_MSG, HttpStatus.NOT_FOUND);
    }
    public static ResponseException educationNotFound() {
        return new ResponseException(EDUCATION_NOT_FOUND_MSG_KEY,EDUCATION_NOT_FOUND_MSG, HttpStatus.NOT_FOUND);
    }
    public static ResponseException employeeNotFound() {
        return new ResponseException(EMPLOYEE_NOT_FOUND_MSG_KEY,EMPLOYEE_NOT_FOUND_MSG, HttpStatus.NOT_FOUND);
    }
    public static ResponseException hiringRequestNotFound() {
        return new ResponseException(HIRING_REQUEST_NOT_FOUND_MSG_KEY,HIRING_REQUEST_NOT_FOUND_MSG, HttpStatus.NOT_FOUND);
    }
    public static ResponseException hiringRequestSkillSetNotFound() {
        return new ResponseException(HIRING_REQUEST_SKILL_SET_NOT_FOUND_MSG_KEY,HIRING_REQUEST_SKILL_SET_NOT_FOUND_MSG, HttpStatus.NOT_FOUND);
    }
    public static ResponseException recruitmentChannelNotFound() {
        return new ResponseException(RECRUITMENT_CHANEL_NOT_FOUND_MSG_KEY,RECRUITMENT_CHANEL_NOT_FOUND_MSG, HttpStatus.NOT_FOUND);
    }
    public static ResponseException skillSetNotFound() {
        return new ResponseException(SKILL_SET_NOT_FOUND_MSG_KEY,SKILL_SET_NOT_FOUND_MSG, HttpStatus.NOT_FOUND);
    }
    public static ResponseException userAccountNotFound() {
        return new ResponseException(USER_ACCOUNT_NOT_FOUND_MSG_KEY,USER_ACCOUNT_NOT_FOUND_MSG, HttpStatus.NOT_FOUND);
    }
    public static ResponseException workingHistoryRecordNotFound() {
        return new ResponseException(WORKING_RECORD_NOT_FOUND_MSG_KEY,WORKING_RECORD_NOT_FOUND_MSG, HttpStatus.NOT_FOUND);
    }
    public static ResponseException workingHistoryRecordSkillSetNotFound() {
        return new ResponseException(WORKING_RECORD_SKILL_SET_NOT_FOUND_MSG_KEY,WORKING_RECORD_SKILL_SET_NOT_FOUND_MSG, HttpStatus.NOT_FOUND);
    }



    public static ResponseException notFound(String messageKey, String message) {
        return new ResponseException(messageKey, message, HttpStatus.NOT_FOUND);
    }

    public static ResponseException badRequest(String messageKey, String message) {
        return new ResponseException(messageKey, message, HttpStatus.BAD_REQUEST);
    }

    public static ResponseException internalServerError(String messageKey, String message) {
        return new ResponseException(messageKey, message, HttpStatus.INTERNAL_SERVER_ERROR);
    }




}
