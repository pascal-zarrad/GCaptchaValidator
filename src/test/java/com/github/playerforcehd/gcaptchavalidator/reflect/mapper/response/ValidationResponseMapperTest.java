package com.github.playerforcehd.gcaptchavalidator.reflect.mapper.response;

import com.github.playerforcehd.gcaptchavalidator.exception.mapper.DataMappingException;
import com.github.playerforcehd.gcaptchavalidator.model.response.ClientType;
import com.github.playerforcehd.gcaptchavalidator.model.response.ValidationError;
import com.github.playerforcehd.gcaptchavalidator.reflect.mapper.Mapper;
import com.github.playerforcehd.gcaptchavalidator.testng.utility.ArrayToMapConverter;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.Date;
import java.util.Map;

/**
 * Tests for {@link com.github.playerforcehd.gcaptchavalidator.reflect.mapper.response.ValidationResponseMapper}
 *
 * @author Pascal Zarrad
 * @since 3.0.0
 */
public class ValidationResponseMapperTest {
    @Test(
        dataProvider = "mapWithUnpredictableResponseTypeDataProvider",
        expectedExceptions = DataMappingException.class
    )
   public void testMapWithUnpredictableResponseType(Map<String, Object> toMap) throws DataMappingException {
        Mapper<Map<String, Object>, Map<String, Object>> validationResponseMapper
            = ValidationResponseMapper.getInstance();

        validationResponseMapper.map(toMap);
   }

   @DataProvider
   public Object[][] mapWithUnpredictableResponseTypeDataProvider() {
        Date testDate = new Date();

        return new Object[][] {
            {
                ArrayToMapConverter.arrayToMap(
                    new Object[][]{
                        { "success", true },
                        { "challenge_ts",  testDate },
                        { "hostname", "localhost" },
                        { "apk_package_name", "TestPkg" }
                    }
                )
            }
        };
   }

    @Test(dataProvider = "mapDataProvider")
    public void testMap(Map<String, Object> toMap, Map<String, Object> expectedResult) {
        Mapper<Map<String, Object>, Map<String, Object>> validationResponseMapper
            = ValidationResponseMapper.getInstance();

        try {
            Map<String, Object> actual = validationResponseMapper.map(toMap);

            Assert.assertEqualsDeep(actual, expectedResult);
        } catch (DataMappingException e) {
            Assert.fail("Did not expect DataMappingException.", e);
        }
    }

    @DataProvider
    public Object[][] mapDataProvider() {
        Date testDate = new Date();

        return new Object[][]{
            // Web - all data
            {
                ArrayToMapConverter.arrayToMap(
                    new Object[][]{
                        { "success", true },
                        { "challenge_ts",  testDate },
                        { "hostname", "localhost" },
                        { "errors", new String[] { "missing-input-secret", "invalid-input-secret" } }
                    }
                ),
                ArrayToMapConverter.arrayToMap(
                    new Object[][]{
                        { "succeeded", true },
                        { "challengeTimestamp",  testDate },
                        { "clientType", ClientType.WEB },
                        { "hostnameOrPackageName", "localhost" },
                        {
                            "errors",
                            new ValidationError[] {
                                ValidationError.MISSING_INPUT_SECRET,
                                ValidationError.INVALID_INPUT_SECRET
                            }
                        }
                    }
                )
            },
            // Web - no errors
            {
                ArrayToMapConverter.arrayToMap(
                    new Object[][]{
                        { "success", true },
                        { "challenge_ts",  testDate },
                        { "hostname", "localhost" }
                    }
                ),
                ArrayToMapConverter.arrayToMap(
                    new Object[][]{
                        { "succeeded", true },
                        { "challengeTimestamp",  testDate },
                        { "clientType", ClientType.WEB },
                        { "hostnameOrPackageName", "localhost" }
                    }
                )
            },
            // Web - no errors & success
            {
                ArrayToMapConverter.arrayToMap(
                    new Object[][]{
                        { "challenge_ts",  testDate },
                        { "hostname", "localhost" }
                    }
                ),
                ArrayToMapConverter.arrayToMap(
                    new Object[][]{
                        { "challengeTimestamp",  testDate },
                        { "clientType", ClientType.WEB },
                        { "hostnameOrPackageName", "localhost" }
                    }
                )
            },
            // Web - no errors & success & challenge_ts
            {
                ArrayToMapConverter.arrayToMap(
                    new Object[][]{
                        { "hostname", "localhost" }
                    }
                ),
                ArrayToMapConverter.arrayToMap(
                    new Object[][]{
                        { "clientType", ClientType.WEB },
                        { "hostnameOrPackageName", "localhost" }
                    }
                )
            },
            // Android - all data
            {
                ArrayToMapConverter.arrayToMap(
                    new Object[][] {
                        { "success", true },
                        { "challenge_ts",  testDate },
                        { "apk_package_name", "TestPkg" },
                        { "errors", new String[] { "missing-input-secret", "invalid-input-secret" } }
                    }
                ),
                ArrayToMapConverter.arrayToMap(
                    new Object[][]{
                        { "succeeded", true },
                        { "challengeTimestamp",  testDate },
                        { "clientType", ClientType.ANDROID },
                        { "hostnameOrPackageName", "TestPkg" },
                        {
                            "errors",
                            new ValidationError[] {
                                ValidationError.MISSING_INPUT_SECRET,
                                ValidationError.INVALID_INPUT_SECRET
                            }
                        }
                    }
                )
            },
            // Android - no errors
            {
                ArrayToMapConverter.arrayToMap(
                    new Object[][] {
                        { "success", true },
                        { "challenge_ts",  testDate },
                        { "apk_package_name", "TestPkg" }
                    }
                ),
                ArrayToMapConverter.arrayToMap(
                    new Object[][]{
                        { "succeeded", true },
                        { "challengeTimestamp",  testDate },
                        { "clientType", ClientType.ANDROID },
                        { "hostnameOrPackageName", "TestPkg" }
                    }
                )
            },
            // Android - no errors & success
            {
                ArrayToMapConverter.arrayToMap(
                    new Object[][] {
                        { "challenge_ts",  testDate },
                        { "apk_package_name", "TestPkg" }
                    }
                ),
                ArrayToMapConverter.arrayToMap(
                    new Object[][]{
                        { "challengeTimestamp",  testDate },
                        { "clientType", ClientType.ANDROID },
                        { "hostnameOrPackageName", "TestPkg" }
                    }
                )
            },
            // Android - no errors & success & challenge_ts
            {
                ArrayToMapConverter.arrayToMap(
                    new Object[][] {
                        { "apk_package_name", "TestPkg" }
                    }
                ),
                ArrayToMapConverter.arrayToMap(
                    new Object[][]{
                        { "clientType", ClientType.ANDROID },
                        { "hostnameOrPackageName", "TestPkg" }
                    }
                )
            },
            // No data
            {
                ArrayToMapConverter.arrayToMap(new Object[][] {}),
                ArrayToMapConverter.arrayToMap(new Object[][] {})
            },
        };
    }
}
