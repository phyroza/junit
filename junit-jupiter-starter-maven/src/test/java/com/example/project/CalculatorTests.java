/*
 * Copyright 2015-2025 the original author or authors.
 *
 * All rights reserved. This program and the accompanying materials are
 * made available under the terms of the Eclipse Public License v2.0 which
 * accompanies this distribution and is available at
 *
 * https://www.eclipse.org/legal/epl-v20.html
 */

package com.example.project;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ArgumentCountValidationMode;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.FieldSource;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.params.provider.Arguments.arguments;

class CalculatorTests {

	@Test
	@DisplayName("1 + 1 = 2")
	void addsTwoNumbers() {
		Calculator calculator = new Calculator();
		assertEquals(2, calculator.add(1, 1), "1 + 1 should equal 2");
	}

	@ParameterizedTest(name = "{0} + {1} = {2}", quoteTextArguments = false)
	@CsvSource(textBlock = """
			0,    1,   1
			1,    2,   3
			49,  51, 100
			1,  100, 101
			""")
	void add(int first, int second, int expectedResult) {
		Calculator calculator = new Calculator();
		assertEquals(expectedResult, calculator.add(first, second),
				() -> first + " + " + second + " should equal " + expectedResult);
	}

    @ParameterizedTest
    @FieldSource("stringIntAndListArguments")
    void testWithMultiArgFieldSource(String str, int num, List<String> list) {
        assertEquals(5, str.length());
        assertTrue(num >=1 && num <=2);
        assertEquals(2, list.size());
    }

    @SuppressWarnings("unused")
    static List<Arguments> stringIntAndListArguments = Arrays.asList(
            arguments("apple", 1, Arrays.asList("a", "b")),
            arguments("lemon", 2, Arrays.asList("x", "y"))
    );

    @ParameterizedTest(argumentCountValidation = ArgumentCountValidationMode.STRICT)
    @CsvSource({ "42" })
    void testWithArgumentCountValidation(int number) {
        assertTrue(number > 0);
    }
}
