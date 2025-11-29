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

import org.jspecify.annotations.NullMarked;
import org.jspecify.annotations.Nullable;
import org.junit.jupiter.api.extension.*;

import java.util.List;
import java.util.stream.Stream;

public class UserIdGeneratorTestInvocationContextProvider implements TestTemplateInvocationContextProvider {
    @Override
    public boolean supportsTestTemplate(ExtensionContext context) {
        return true;
    }

    @Override
    public Stream<? extends TestTemplateInvocationContext> provideTestTemplateInvocationContexts(ExtensionContext context) {
        return Stream.of(
                createDisabledContext(
                        new UserIdGeneratorImplTestCase(
                                "disabledTest",
                                false,
                                "Tom",
                                "Rom",
                                "TRom")
                ),
                createEnabledContext(
                        new UserIdGeneratorImplTestCase(
                                "enabledTest",
                                true,
                                "Tom",
                                "Rom",
                                "baelTRom")
                )
        );
    }

    private TestTemplateInvocationContext createDisabledContext(UserIdGeneratorImplTestCase userIdGeneratorTestCase) {
        return new TestTemplateInvocationContext(){
            @Override
            public String getDisplayName(int invocationIndex) {
                return userIdGeneratorTestCase.displayName();
            }

            @Override
            public List<Extension> getAdditionalExtensions() {
                return List.of(
                        new UserIdGeneratorParameterResolver(userIdGeneratorTestCase),
                        (BeforeTestExecutionCallback) extCtx ->
                                System.out.println("BeforeTestExecutionCallback:Disabled context"),
                        (AfterTestExecutionCallback) extCtx ->
                                System.out.println("AfterTestExecutionCallback:Disabled context")
                );
            }
        };
    }

    private TestTemplateInvocationContext createEnabledContext(UserIdGeneratorImplTestCase userIdGeneratorTestCase) {
        return new TestTemplateInvocationContext(){
            @Override
            public String getDisplayName(int invocationIndex) {
                return userIdGeneratorTestCase.displayName();
            }

            @Override
            public List<Extension> getAdditionalExtensions() {
                return List.of(
                        new UserIdGeneratorParameterResolver(userIdGeneratorTestCase),
                        (BeforeTestExecutionCallback) extCtx ->
                                System.out.println("BeforeTestExecutionCallback:Enabled context"),
                        (AfterTestExecutionCallback) extCtx ->
                                System.out.println("AfterTestExecutionCallback:Enabled context")
                );
            }
        };
    }

    private static class UserIdGeneratorParameterResolver implements ParameterResolver {
        private final UserIdGeneratorImplTestCase userIdGeneratorTestCase;

        public UserIdGeneratorParameterResolver(UserIdGeneratorImplTestCase userIdGeneratorTestCase) {
            this.userIdGeneratorTestCase = userIdGeneratorTestCase;
        }

        @Override
        @NullMarked
        public boolean supportsParameter(ParameterContext parameterContext, ExtensionContext extensionContext)
                throws ParameterResolutionException {
            return parameterContext.getParameter().getType() == UserIdGeneratorImplTestCase.class;
        }

        @Override
        public @Nullable Object resolveParameter(ParameterContext parameterContext, ExtensionContext extensionContext)
                throws ParameterResolutionException {
            return userIdGeneratorTestCase;
        }
    }
}
