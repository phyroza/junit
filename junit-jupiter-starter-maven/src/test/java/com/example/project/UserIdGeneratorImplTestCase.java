package com.example.project;

public record UserIdGeneratorImplTestCase(
        String displayName,
        boolean isFeatureEnabled,
        String firstName,
        String lastName,
        String expectedUserId
) {}

