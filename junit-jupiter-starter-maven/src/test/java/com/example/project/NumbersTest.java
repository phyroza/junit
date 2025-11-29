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

import org.junit.jupiter.params.ArgumentCountValidationMode;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.assertTrue;

class NumbersTest {
    @ParameterizedTest(argumentCountValidation = ArgumentCountValidationMode.STRICT)
    @ValueSource(ints = {1, 3, 5, -3, 15, Integer.MAX_VALUE})
    void isOdd(int aNumber) {
        assertTrue(Numbers.isOdd(aNumber));
    }
}