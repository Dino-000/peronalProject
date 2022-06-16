-- education table

INSERT INTO public.education (
 degree,  major, school_name, prestige_rate) VALUES (
 'Bachelor', 'Business Administration', 'Troy University', '20.1');
INSERT INTO public.education (
 degree,  major, school_name, prestige_rate) VALUES (
 'Bachelor',  'Computer Science', 'Troy University', '20.1');
INSERT INTO public.education (
 degree,  major, school_name, prestige_rate) VALUES (
 'Bachelor',  'Computer NetWork', 'HCMUS', '39.5');
INSERT INTO public.education (
 degree,  major, school_name, prestige_rate) VALUES (
 'Bachelor',  'Data Sciences', 'HCMUT', '41.5');
INSERT INTO public.education (
 degree,  major, school_name, prestige_rate) VALUES (
 'Bachelor',  'SoftWare Engineer', 'UIT', '34.5');
INSERT INTO public.education (
 degree,  major, school_name, prestige_rate) VALUES (
 'Bachelor',  'Computer Science', 'NUS', '78.6');
INSERT INTO public.education (
 degree,  major, school_name, prestige_rate) VALUES (
 'Bachelor',  'Financial', 'Yale University', '81.5');
INSERT INTO public.education (
 degree,  major, school_name, prestige_rate) VALUES (
 'Bachelor',  'Public Art', 'MIT', '91.6');
INSERT INTO public.education (
 degree,  major, school_name, prestige_rate) VALUES (
 'Bachelor',  'Public Relation', 'Curtin University', '75.9');
/*INSERT INTO public.education (
 degree,  major, school_name, prestige_rate) VALUES (
 'Bachelor',  'Nursing', 'HongKong University', '77.3');*/

-- candidate table

INSERT INTO public.candidate (
 date_of_birth, gpa, location, name, occupation, seniority) VALUES (
 '1999-03-7', '3.1', 'Ho Chi Minh City', 'Ho Thanh Son', 'Software Engineer', 'JUNIOR')
 ;

INSERT INTO public.candidate (
 date_of_birth, gpa, location, name, occupation, seniority) VALUES (
 '1991-11-2', '3.7', 'Ho Chi Minh City', 'Tran Van Dino', 'Hr', 'FRESHER')
 ;

INSERT INTO public.candidate (
 date_of_birth, gpa, location, name, occupation, seniority) VALUES (
 '1977-11-7', '2.4', 'Texas', 'Johny Deep', 'Tester', 'SENIOR')
 ;

INSERT INTO public.candidate (
 date_of_birth, gpa, location, name, occupation, seniority) VALUES (
 '1972-10-21', '1.8', 'Washington.DC', 'Tony Bush', 'Product Owner', 'SENIOR')
 ;

INSERT INTO public.candidate (
 date_of_birth, gpa, location, name, occupation, seniority) VALUES (
 '1985-02-28', '2.4', 'Can Tho City', 'Nguyen Thi Hanh', 'Technical Manager', 'MANAGER')
 ;

INSERT INTO public.candidate (
 date_of_birth, gpa, location, name, occupation, seniority) VALUES (
 '1959-09-17', '1.7', 'PhnomPenh City', 'Jirathip Soongthanenii', 'Product Owner', 'TEAM_LEAD')
 ;

INSERT INTO public.candidate (
 date_of_birth, gpa, location, name, occupation, seniority) VALUES (
 '1989-02-01', '2.6', 'Dubai City', 'Ahmut Halah', 'Tester', 'MID_LEVEL')
 ;

INSERT INTO public.candidate (
 date_of_birth, gpa, location, name, occupation, seniority) VALUES (
 '1972-02-14', '2.2', 'Perth City', 'Goerge Cruise', 'DevOps Engineer', 'DIRECTOR')
 ;

INSERT INTO public.candidate (
 date_of_birth, gpa, location, name, occupation, seniority) VALUES (
 '2001-03-31', '3.3', 'Victoria City', 'Mupalla Hen', 'Software Engineer', 'INTERN')
 ;

INSERT INTO public.candidate (
 date_of_birth, gpa, location, name, occupation, seniority) VALUES (
 '2003-08-03', '2.5', 'Kiev City', 'Tonschovky Sheva', 'Scum Master', 'JUNIOR')
 ;

--certification

INSERT INTO public.certification (
  name_of_certification, type, issuer_name) VALUES (
 'ISTQB Foundation Level', 'Software Testing', 'ISTQB');

INSERT INTO public.certification (
  name_of_certification, type, issuer_name) VALUES (
 'ISTQB Advanced Level ', 'Software Testing', 'ISTQB');
INSERT INTO public.certification (
  name_of_certification, type, issuer_name) VALUES (
 'ISTQB Expert Level', 'Software Testing', 'ISTQB');
INSERT INTO public.certification (
  name_of_certification, type, issuer_name) VALUES (
 'ISTQB Foundation Level', 'Software Testing', 'PMI');
INSERT INTO public.certification (
  name_of_certification, type, issuer_name) VALUES (
 'PMP', 'Project Management', 'PMI');
INSERT INTO public.certification (
  name_of_certification, type, issuer_name) VALUES (
 'PMI-PBA', 'Business Analysis', 'PMI');
INSERT INTO public.certification (
  name_of_certification, type, issuer_name) VALUES (
 'PMI-ACP', 'Associate Project Management', 'PMI');
INSERT INTO public.certification (
  name_of_certification, type, issuer_name) VALUES (
 'AZ-400', 'DevOps', 'Microsoft');
/*INSERT INTO public.certification (
  name_of_certification, type, issuer_name) VALUES (
 'A1-100', 'AI', 'Microoft');
INSERT INTO public.certification (
  name_of_certification, type, issuer_name) VALUES (
 'FOUNDATIONAL', 'Cloud Service', 'AWS');*/

--department
INSERT INTO public.department (
 headcount, managerid, name, quantity_of_hiring_manager) VALUES (
 12, 'IT1', 'IT', 2);

INSERT INTO public.department (
 headcount, managerid, name, quantity_of_hiring_manager) VALUES (
 17, 'HR1', 'HR', 1);

INSERT INTO public.department (
 headcount, managerid, name, quantity_of_hiring_manager) VALUES (
 7, 'LOGISTIC1', 'LOGISTIC', 1);

INSERT INTO public.department (
 headcount, managerid, name, quantity_of_hiring_manager) VALUES (
 3, 'ADMIN1', 'ADMIN', 1);

INSERT INTO public.department (
 headcount, managerid, name, quantity_of_hiring_manager) VALUES (
 4, 'FINANCE1', 'FINANCE', 1);


--Employee
INSERT INTO public.employee (
 employee_id, date_of_birth, name, team, department_id) VALUES (
 'IT1', '1990-02-12', 'Tran Thanh Hoa', 'Ecko', 1);

INSERT INTO public.employee (
 employee_id, date_of_birth, name, team, department_id) VALUES (
 'IT2', '1991-03-08', 'Bill Van Gate', 'Eagle', 1);

INSERT INTO public.employee (
 employee_id, date_of_birth, name, team, department_id) VALUES (
 'IT3', '1992-02-08', 'Eric ', 'Cat', 1);

INSERT INTO public.employee (
 employee_id, date_of_birth, name, team, department_id) VALUES (
 'IT4', '1993-04-02', 'Tran Thanh Luan', 'Luna', 1);

INSERT INTO public.employee (
 employee_id, date_of_birth, name, team, department_id) VALUES (
 'HR1', '1994-05-10', 'Tran Thanh Minh', 'Ethirium', 2);

INSERT INTO public.employee (
 employee_id, date_of_birth, name, team, department_id) VALUES (
 'HR2', '1990-06-02', 'Tran Thanh Tung', 'Star', 2);







--skillset


INSERT INTO public.skill_set ( industry_category, level, name, type) VALUES (
 'IT', 'Advance', 'React JS', 'Front End');

INSERT INTO public.skill_set ( industry_category, level, name, type) VALUES (
 'IT', 'Advance', 'Java', 'Back End');


INSERT INTO public.skill_set ( industry_category, level, name, type) VALUES (
 'IT', 'Advance', 'Selenium', 'Testing');

INSERT INTO public.skill_set ( industry_category, level, name, type) VALUES (
 'IT', 'Advance', 'Kurbernette', 'DevOps');

INSERT INTO public.skill_set ( industry_category, level, name, type) VALUES (
 'IT', 'Advance', 'AWS', 'Cloud Service');

INSERT INTO public.skill_set ( industry_category, level, name, type) VALUES ( 'IT', 'Medium', 'React JS', 'Front End');

INSERT INTO public.skill_set ( industry_category, level, name, type) VALUES (
 'IT', 'Medium', 'Java', 'Back End');

INSERT INTO public.skill_set ( industry_category, level, name, type) VALUES (
 'IT', 'Medium', 'Selenium', 'Testing');

INSERT INTO public.skill_set ( industry_category, level, name, type) VALUES (
 'IT', 'Medium', 'Kurbernette', 'DevOps');

INSERT INTO public.skill_set ( industry_category, level, name, type) VALUES (
 'IT', 'Medium', 'AWS', 'Cloud Service');

--hiring request
INSERT INTO public.hiring_request (
  on_boarding_date, "position", specific_benefit, department_id, hiring_manager_id,budget) VALUES (
  '2022-06-06', 'Front End', 'work remote', 1, 1,1000);
INSERT INTO public.hiring_request (
  on_boarding_date, "position", specific_benefit, department_id, hiring_manager_id,budget) VALUES (
  '2022-06-06', 'DevOps', 'voucher beer club', 1, 2,1200);
INSERT INTO public.hiring_request (
  on_boarding_date, "position", specific_benefit, department_id, hiring_manager_id, budget) VALUES (
  '2022-06-06', 'Back End', 'spa discount', 1, 3, 1100);
INSERT INTO public.hiring_request (
  on_boarding_date, "position", specific_benefit, department_id, hiring_manager_id, budget) VALUES (
  '2022-06-06', 'UX/UI Designer', 'bonus bug', 1, 4, 900);

--recruitment channel
INSERT INTO public.recruitment_chanel (
 admin_account, annual_membership_fee, conversion_rate, name, number_of_successful_placement) VALUES (
 'admin123', '100', '0.5', 'Vietnamwork', '2');
INSERT INTO public.recruitment_chanel (
 admin_account, annual_membership_fee, conversion_rate, name, number_of_successful_placement) VALUES (
 'admin123', '200', '0.3', 'ItViec', '4');
INSERT INTO public.recruitment_chanel (
 admin_account, annual_membership_fee, conversion_rate, name, number_of_successful_placement) VALUES (
 'admin123', '300', '0.2', 'Linkedin', '3');
INSERT INTO public.recruitment_chanel (
 admin_account, annual_membership_fee, conversion_rate, name, number_of_successful_placement) VALUES (
 'admin123', '400', '0.1', 'FaceBook', '5');

--aplication form

INSERT INTO public.application_form (
 notice_periods, salary_expectation, submitted_date, candidate_id, hiring_request_id, hr_officer_id, recruitment_chanel_id) VALUES (
 30, 2000, '2022-05-05', '1', '1', '5', '1');

INSERT INTO public.application_form (
 notice_periods, salary_expectation, submitted_date, candidate_id, hiring_request_id, hr_officer_id, recruitment_chanel_id) VALUES (
 '45', '3000', '2022-05-05', '2', '1', '5', '2');


INSERT INTO public.application_form (
 notice_periods, salary_expectation, submitted_date, candidate_id, hiring_request_id, hr_officer_id, recruitment_chanel_id) VALUES (
 '7', '4000', '2022-05-05', '4', '1', '6', '1');


INSERT INTO public.application_form (
 notice_periods, salary_expectation, submitted_date, candidate_id, hiring_request_id, hr_officer_id, recruitment_chanel_id) VALUES (
 '45', '5000', '2022-05-05', '3', '1', '5', '3');


INSERT INTO public.application_form (
 notice_periods, salary_expectation, submitted_date, candidate_id, hiring_request_id, hr_officer_id, recruitment_chanel_id) VALUES (
 '30', '1000', '2022-05-05', '1', '1', '6', '1');



--cand-cert



INSERT INTO public.candidate_certification (
 expired_date, issued_date, candidate_id, certification_id) VALUES (
 '2022-02-02', '2020-02-02', '1', '1');

INSERT INTO public.candidate_certification (
 expired_date, issued_date, candidate_id, certification_id) VALUES (
 '2022-01-01', '2020-01-01', '1', '2');

INSERT INTO public.candidate_certification (
 expired_date, issued_date, candidate_id, certification_id) VALUES (
 '2021-11-11', '2019-11-11', '2', '1');

INSERT INTO public.candidate_certification (
 expired_date, issued_date, candidate_id, certification_id) VALUES (
 '2019-06-06', '2017-02-02', '2', '2');



--cand - edu


INSERT INTO public.candidate_education (
 graduation_year, candidate_id, education_id) VALUES (
 2019, 1, 2);

INSERT INTO public.candidate_education (
 graduation_year, candidate_id, education_id) VALUES (
 2021, 2, 2);

INSERT INTO public.candidate_education (
 graduation_year, candidate_id, education_id) VALUES (
 1998, 3, 2);

INSERT INTO public.candidate_education (
 graduation_year, candidate_id, education_id) VALUES (
 2015, 4, 4);



--cand-skill

INSERT INTO public.candidate_skill_set (
 candidate_id, skill_set_id) VALUES (
 '1', '2');

INSERT INTO public.candidate_skill_set (
 candidate_id, skill_set_id) VALUES (
 '1', '3');

INSERT INTO public.candidate_skill_set (
 candidate_id, skill_set_id) VALUES (
 '1', '4');

INSERT INTO public.candidate_skill_set (
 candidate_id, skill_set_id) VALUES (
 '2', '6');



--request - skill

INSERT INTO public.hiring_request_skill_set (
 hiring_request_id, skill_set_id) VALUES (
 '1', '1');
INSERT INTO public.hiring_request_skill_set (
 hiring_request_id, skill_set_id) VALUES (
 '1', '4');
INSERT INTO public.hiring_request_skill_set (
 hiring_request_id, skill_set_id) VALUES (
 '1', '8');
INSERT INTO public.hiring_request_skill_set (
 hiring_request_id, skill_set_id) VALUES (
 '2', '1');


--working-record

INSERT INTO public.working_history_record (
 client, company_name, joined_date, "position", project_name, references_people_phone, resignation_date, responsibility, team_size, candidate_id,job_type) VALUES (
 'Manabie', 'SEA', '2017-01-01', 'lead Front End', 'SEA Talk', '0123', '2018-01-01', 'fix bug', '10', '1','onsite');

INSERT INTO public.working_history_record (
 client, company_name, joined_date, "position", project_name, references_people_phone, resignation_date, responsibility, team_size, candidate_id,job_type) VALUES (
 'Axon', 'Amazon', '2018-01-01', 'Solution Architect', 'Kinlde Server', '0123', '2019-01-01', 'fix bug', '10', '1','part-time');

INSERT INTO public.working_history_record (
 client, company_name, joined_date, "position", project_name, references_people_phone, resignation_date, responsibility, team_size, candidate_id,job_type) VALUES (
 'Google', 'SEA', '2019-01-01', 'Delivery Manager', 'VPN', '0123', '2020-01-01', 'fix bug', '10', '1','fulltime');

INSERT INTO public.working_history_record (
 client, company_name, joined_date, "position", project_name, references_people_phone, resignation_date, responsibility, team_size, candidate_id,job_type) VALUES (
 'PhongVu', 'SEA', '2017-01-01', 'lead Front End', 'SEA Talk', '0123', '2018-01-01', 'fix bug', '10', '2','freelance');


--working-record skillset
INSERT INTO public.working_history_record_skill_set ( skill_set_id, working_history_record_id) VALUES (
 '1', '1');

INSERT INTO public.working_history_record_skill_set ( skill_set_id, working_history_record_id) VALUES (
 '10', '2');

INSERT INTO public.working_history_record_skill_set ( skill_set_id, working_history_record_id) VALUES (
 '4', '1');

INSERT INTO public.working_history_record_skill_set ( skill_set_id, working_history_record_id) VALUES (
 '2', '1');

--new user
INSERT INTO public.users (
active, password, user_name) VALUES (
true::boolean, '$2a$10$hD9FCjv8VBbC6uGZRMkydezWDZQlEHREeVndhwjbKfuAd5qT.toYW'::character varying, 'hrofficer01'::character varying)
 returning id;

 INSERT INTO public.users (
active, password, user_name) VALUES (
true::boolean, '$2a$10$hD9FCjv8VBbC6uGZRMkydezWDZQlEHREeVndhwjbKfuAd5qT.toYW'::character varying, 'hiringmanager01'::character varying)
 returning id;


--user & role
INSERT INTO public.user_role_assignment (
user_id, role) VALUES (
'2'::integer, 'ROLE_HIRING_MANAGER'::character varying)
 returning id;

 INSERT INTO public.user_role_assignment (
 role, user_id) VALUES (
 'ROLE_HR'::character varying, '1'::integer)
  returning id;




