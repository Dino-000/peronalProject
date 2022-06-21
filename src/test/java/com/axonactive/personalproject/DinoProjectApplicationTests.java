package com.axonactive.personalproject;

import com.axonactive.personalproject.service.ApplicationFormService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class DinoProjectApplicationTests {
@Autowired
	ApplicationFormService applicationFormService;
	@Test
	void testFindBySubmittedDateBetween_shouldReturnLargerThan0_WhenInputDayForm2000To2022() {
		Assertions.assertNotEquals(0,applicationFormService.findBySubmittedDateBetween("2000-01-02","2022-12-12"));
	}


}
