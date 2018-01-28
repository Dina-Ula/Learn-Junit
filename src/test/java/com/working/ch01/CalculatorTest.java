package com.working.ch01;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.working.ch01.Calculator;

public class CalculatorTest {

	@Test
	public void testAdd() {
		Calculator calculator = new Calculator();
		double result = calculator.add(10, 50);
		//The delta difference can be more or less than 1
		assertEquals(59, result, 1);
		assertEquals(60, result, 1);
		assertEquals(61, result, 1);
	}
}
