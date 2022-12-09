package com.devops.crackle;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class CrackleApplicationTests {
	Calculator underTest = new Calculator();
	@Test
	void itShouldAddTwoNumbers() {
//Given
		int numberOne = 20;
		int numberTwo =30;

//		when
		int result = underTest.add(numberOne,numberTwo);

//		Then
		assertThat(result).isEqualTo(50);

		}

	class Calculator {
		int add(int one, int two){
			return one + two;
		}
	}

}
