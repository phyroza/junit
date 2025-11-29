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

import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;
import org.junit.jupiter.params.support.ParameterDeclarations;

import java.util.stream.Stream;

public class isBlankArgumentsProvider implements ArgumentsProvider {
    @Override
    public Stream<? extends Arguments> provideArguments(ParameterDeclarations parameters, ExtensionContext context) {
        return Stream.of(
                Arguments.of(""),
                Arguments.of(" "),
                Arguments.of("  "),
                Arguments.of((String) null)
        );
    }
}
