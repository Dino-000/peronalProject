package com.axonactive.personalproject.service.impl;

import com.axonactive.personalproject.service.ApplicationFormService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class ApplicationFormServiceImplTest {
    @Autowired
    ApplicationFormService applicationFormService;

    @Test
    void testFindBySubmittedDateBetween_shouldReturnListSizeLargerThan0_WhenInputDayForm2000To2022() {
        Assertions.assertNotEquals(0,applicationFormService.findBySubmittedDateBetween("2000-01-02","2022-12-12"));
    }

    @Test
    void testFindByHiringRequestHiringManagerId_shouldReturnListSizeLargerThan0_WhenInputDayForm2000To2022() {
        Assertions.assertNotEquals(0,applicationFormService.findBySubmittedDateBetween("2000-01-02","2022-12-12"));
    }
}
