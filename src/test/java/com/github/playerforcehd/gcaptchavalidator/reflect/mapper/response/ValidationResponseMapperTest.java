package com.github.playerforcehd.gcaptchavalidator.reflect.mapper.response;

import com.github.playerforcehd.gcaptchavalidator.reflect.mapper.Mapper;
import com.github.playerforcehd.gcaptchavalidator.reflect.mapper.response.ValidationResponseMapper;
import org.junit.Before;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.collections.Maps;

import java.util.HashMap;
import java.util.Map;

/**
 * Tests for {@link com.github.playerforcehd.gcaptchavalidator.reflect.mapper.response.ValidationResponseMapper}
 *
 * @author Pascal Zarrad
 * @since 3.0.0
 */
public class ValidationResponseMapperTest {

    /**
     * The mapper that is tested
     */
    private Mapper<Map<String, Object>, Map<String, Object>> validationResponseMapper;

    @Before
    public void before() {
        this.validationResponseMapper = ValidationResponseMapper.getInstance();
    }

    @Test(dataProvider = "mapDataProvider")
    public void testMap() {

    }

    @DataProvider
    public Object[][] mapDataProvider() {
        return new Object[][] {
            {Maps.newHashMap()}
        };
    }
}
