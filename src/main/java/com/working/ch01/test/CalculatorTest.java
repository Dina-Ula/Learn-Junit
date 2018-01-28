package com.working.ch01.test;

import com.working.ch01.Calculator;

public class CalculatorTest {
	private int nbErrors = 0;

	public void testAdd() {
		Calculator calculator = new Calculator();
		double result = calculator.add(10, 10);
		if (20 != result) {
			throw new IllegalStateException("Bad Result: " + result);
		}
	}

	public static void main(String args[]) {
		// Type 1
		Calculator calculator = new Calculator();
		double result = calculator.add(10, 50);
		if (result != 60) {
			System.out.println("Bad result: " + result);
		}

		// Type 2
		CalculatorTest calculatorTest = new CalculatorTest();
		try {
			calculatorTest.testAdd();
		} catch (Throwable e) {
			calculatorTest.nbErrors++;
			e.printStackTrace();
		}
		if (calculatorTest.nbErrors > 0) {
			throw new IllegalStateException("There were " + calculatorTest.nbErrors + " error(s).");
		}
	}
}
