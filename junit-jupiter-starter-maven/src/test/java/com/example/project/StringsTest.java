/*
 *
 *  * Copyright 2015-2025 the original author or authors.
 *  *
 *  * All rights reserved. This program and the accompanying materials are
 *  * made available under the terms of the Eclipse Public License v2.0 which
 *  * accompanies this distribution and is available at
 *  *
 *  * https://www.eclipse.org/legal/epl-v20.html
 *
 */

package com.example.project;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.*;

import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class StringsTest {

    @ParameterizedTest
    @ValueSource(strings = {"", " ", "  "})
    @NullSource
    void isBlank_valueSource(String inputString) {
        assertTrue(Strings.isBlank(inputString));
    }

    @ParameterizedTest
    @CsvSource(value = {",true", " ,true", "a,false"})
    void isBlank_csvSource(String inputString, boolean expBlank) {
        assertEquals(expBlank, Strings.isBlank(inputString));
    }

    @ParameterizedTest
    @FieldSource("isBlankStrings")
    void isBlank_fieldSource(String inputString, boolean expBlank) {
        assertEquals(expBlank, Strings.isBlank(inputString));
    }

    @SuppressWarnings("unused")
    static List<Arguments> isBlankStrings = List.of(
            Arguments.of("", true),
            Arguments.of(" ", true),
            Arguments.of("  ", true),
            Arguments.of("a", false)
    );

    @ParameterizedTest
    @MethodSource("isBlankStrings")
    void isBlank_methodSource(String inputString, boolean expBlank) {
        assertEquals(expBlank, Strings.isBlank(inputString));
    }

    public static Stream<Arguments> isBlankStrings() {
        return Stream.of(
                Arguments.arguments("", true),
                Arguments.arguments(" ", true),
                Arguments.arguments("   ", true),
                Arguments.arguments("a", false)
        );
    }

    @ParameterizedTest
    @ArgumentsSource(isBlankArgumentsProvider.class)
    void isBlank_argumentsSource(String inputString) {
        assertTrue(Strings.isBlank(inputString));
    }
}