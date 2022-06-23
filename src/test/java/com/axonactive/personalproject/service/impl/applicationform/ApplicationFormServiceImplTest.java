package com.axonactive.personalproject.service.impl.applicationform;

import com.axonactive.personalproject.exception.EntityNotFoundException;
import com.axonactive.personalproject.service.ApplicationFormService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)

public class ApplicationFormServiceImplTest {
    @Autowired
    ApplicationFormService applicationFormService;

    @Test
    void testFindBySubmittedDateBetween_shouldReturnListSizeLargerThan0_WhenInputDayForm2000To2022() {
        assertEquals(5,applicationFormService.findBySubmittedDateBetween(LocalDate.parse("2000-01-02"),LocalDate.parse("2022-12-12")).size());
    }

    @Test
    void testFindByHiringRequestHiringManagerId_shouldReturnListSizeLargerThan0_WhenInputId1() {
        assertEquals(5,applicationFormService.findByHiringRequestHiringManagerId(1).size());
    }

    @Test
    void testGetSalary_shouldReturn2000_WhenInputId1() throws EntityNotFoundException {
        assertEquals(2000,applicationFormService.getSalary(1));
    }
}
