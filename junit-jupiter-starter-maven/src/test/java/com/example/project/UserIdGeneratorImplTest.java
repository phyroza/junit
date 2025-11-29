package com.example.project;

import org.junit.jupiter.api.TestTemplate;
import org.junit.jupiter.api.extension.ExtendWith;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

class UserIdGeneratorImplTest {
    @TestTemplate
    @ExtendWith(UserIdGeneratorTestInvocationContextProvider.class)
    public void userGenerationTest(UserIdGeneratorImplTestCase testCase){
        UserIdGeneratorImpl generator = new UserIdGeneratorImpl(testCase.isFeatureEnabled());
        String userId = generator.generate(testCase.firstName(), testCase.lastName());
        assertThat(userId, is(equalTo(testCase.expectedUserId())));
    }
}