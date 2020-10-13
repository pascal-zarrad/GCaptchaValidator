package com.github.playerforcehd.gcaptchavalidator.model.response;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.Date;

/**
 * Test if the factory methods of ValidationResponse work as intended
 *
 * @author Pascal Zarrad
 * @since 3.0.0
 */
public class ValidationResponseTest {
    /**
     * The expected validation errors
     */
    private final ValidationError[] expectedErrors = {
        ValidationError.MISSING_INPUT_RESPONSE,
        ValidationError.INVALID_INPUT_SECRET
    };

    @Test(dataProvider = "createValidationResponseDataProvider")
    public void testCreateValidationResponse(
        boolean expectedSucceeded,
        Date expectedChallengeTimestamp,
        ClientType expectedClientType,
        String expectedHostnameOrPackageName,
        ValidationError[] expectedErrors
    ) {
        ValidationResponse actual = ValidationResponse.create(
            expectedSucceeded,
            expectedChallengeTimestamp,
            expectedClientType,
            expectedHostnameOrPackageName,
            expectedErrors
        );

        Assert.assertTrue(actual instanceof SiteVerifyResponse);
        Assert.assertEquals(actual.hasSucceeded(), expectedSucceeded);
        Assert.assertEquals(actual.getChallengeTimestamp(), expectedChallengeTimestamp);
        Assert.assertEquals(actual.getClientType(), expectedClientType);
        Assert.assertEquals(actual.getHostnameOrPackageName(), expectedHostnameOrPackageName);
        Assert.assertEquals(actual.getErrors(), expectedErrors);
    }

    @DataProvider
    public Object[][] createValidationResponseDataProvider() {
        return new Object[][] {
            {
                true,
                new Date(),
                ClientType.WEB,
                "127.0.0.1",
                new ValidationError[] { ValidationError.MISSING_INPUT_RESPONSE, ValidationError.INVALID_INPUT_SECRET }
            }
        };
    }
}
