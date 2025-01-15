package com.Tiago.calculator;

import com.Tiago.calculator.service.CalculatorService;
import com.Tiago.calculator.result.CalculatorResult;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import static org.junit.jupiter.api.Assertions.assertEquals;


/*
 * Class responsible for tests
 */
class CalculatorApplicationTests {

	private final CalculatorService calculatorService = new CalculatorService();

	/*
	 * Does the calculation, and compares results with the expected
	 */
	@Test
    void testAdd() {
        CalculatorResult result = calculatorService.add("1", "2");
        assertEquals("3", result.getResult());
    }

    @Test
    void testSub() {
        CalculatorResult result = calculatorService.sub("5", "3");
        assertEquals("2", result.getResult());
    }

    @Test
    void testMul() {
        CalculatorResult result = calculatorService.mul("3", "4");
        assertEquals("12", result.getResult());
    }

    @Test
    void testDiv() {
        CalculatorResult result = calculatorService.div("6", "2");
        assertEquals("3", result.getResult());
    }


}
