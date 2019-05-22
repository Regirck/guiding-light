package com.kriger.guidinglight.util;

import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;

import static com.kriger.guidinglight.util.RegistrationAndLoginUtil.generateRandomActivationKey;
import static org.junit.jupiter.api.Assertions.*;

class RegistrationAndLoginUtilTest {

    @Test
    @RepeatedTest(100)
    void generateRandomActivationKeyReturnSizeIsCorrect() {
        String activationKey = generateRandomActivationKey();
        assertEquals(activationKey.length(),16);
    }
}