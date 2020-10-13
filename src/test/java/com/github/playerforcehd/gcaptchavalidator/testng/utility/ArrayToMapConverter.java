package com.github.playerforcehd.gcaptchavalidator.testng.utility;

import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.collections.Maps;

import java.util.HashMap;
import java.util.Map;

/**
 * Utility for testing to allow simple creation of data providers that
 * need to provide {@link java.util.Map}s for testing.
 *
 * @author Pascal Zarrad
 * @since 3.0.0
 */
public class ArrayToMapConverter {

    /**
     * Small check to verify our {@link #arrayToMap(Object[][])} helper method
     * works as intended.
     */
    @Test
    public void testArrayToMap() {
        Object[][] testData = new Object[][] {
            {"Some", "Data"},
            {"Some bit", "other Data"}
        };

        Map<String, Object> expected = Maps.newHashMap();
        expected.put("Some", "Data");
        expected.put("Some bit", "other Data");

        Map<String, Object> actual = ArrayToMapConverter.arrayToMap(testData);

        Assert.assertEquals(actual, expected);
    }

    /**
     * Convert an two dimensional array to a map.
     * Makes it easier to test methods that use nested data using maps,
     * as data providers can be created far easier.
     *
     * @param data The data to convert
     * @return The map that contains the data
     */
    public static Map<String, Object> arrayToMap(Object[][] data) {
        Map<String, Object> map = Maps.newHashMap();

        for (Object[] datum : data) {
            map.put((String)datum[0], datum[1]);
        }

        return map;
    }
}
