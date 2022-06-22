package com.axonactive.personalproject.service.impl.candidate;

import com.axonactive.personalproject.exception.EntityNotFoundException;
import com.axonactive.personalproject.service.ApplicationFormService;
import com.axonactive.personalproject.service.CandidateService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)

public class CandidateServiceImplTest {
    @Autowired
    CandidateService candidateService;

    @Test
    void testFindBySalaryExpectationLessThanAndCandidateSkillSet_shouldReturnListSizeLargerThan0_WhenInputJavaAnd3000() {
        assertNotEquals(0,candidateService.findBySalaryExpectationLessThanAndCandidateSkillSet(3000.0,"Java").size());
        assertEquals(1,candidateService.findBySalaryExpectationLessThanAndCandidateSkillSet(3000.0,"Java").size());
    }

    @Test
    void testFindByExperiencedTeamSizeLargerThan_shouldReturn2_WhenInput10AndCheckSize() {
        assertEquals(2,candidateService.findByExperiencedTeamSizeLargerThan(10).size());
    }

    @Test
    void testFindByExperiencedWithJobType_shouldReturn2_WhenInputOnsiteAndCheckSize() {
        assertEquals(1,candidateService.findByExperiencedWithJobType("onsite").size());
    }

    @Test
    void testFindByLocationAndSkillSetAndSeniority_shouldReturn1_WhenInputHoChiMinhCityAndCheckSize() throws EntityNotFoundException {
        assertEquals(1,candidateService.findByLocationAndSkillSetAndSeniority("Ho Chi Minh City","Java","JUNIOR").size());
    }
    @Test
    void testFindBySalaryExpectationGreaterThanHiringRequestBudget_shouldReturn1_WhenInputId1AndCheckSize() throws EntityNotFoundException {
        assertEquals(1,candidateService.findBySalaryExpectationGreaterThanHiringRequestBudget(1).size());
    }
    @Test
    void testFindByExperiencesInSpecificCompany_shouldReturn2_WhenInputSEAAndCheckSize() throws EntityNotFoundException {
        assertEquals(2,candidateService.findByExperiencesInSpecificCompany("SEA").size());
    }
    @Test
    void testFindByEducation_shouldReturn3_WhenInputTroyUniversityAndCheckSize() throws EntityNotFoundException {
        assertEquals(3,candidateService.findByEducation("Troy University").size());
    }
    @Test
    void testFindByCertification_shouldReturn2_WhenInputISTQBFoundationLevelAndCheckSize() throws EntityNotFoundException {
        assertEquals(2,candidateService.findByCertification("ISTQB Foundation Level").size());
    }
    @Test
    void testFindPortfolio_shouldReturnHoThanhSon_WhenInputId1AndCheckCandidateName() throws EntityNotFoundException {
        assertEquals("Ho Thanh Son",candidateService.findPortfolio(1).getCandidate().getName());
    }


}
