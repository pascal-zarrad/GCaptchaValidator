package com.github.playerforcehd.gcaptchavalidator.reflect.mapper;

import com.github.playerforcehd.gcaptchavalidator.exception.mapper.DataMappingException;

/**
 * Simple {@link Mapper} interface that can be used to create mappers.
 *
 * Mappers in this library are for example used to determine which external
 * key refers to which field in a class.
 *
 * Mappers should be created as singletons.
 *
 * @author Pascal Zarrad
 * @since 3.0.0
 *
 * @param <I> Type of the input values
 * @param <O> Type of the mapped output values
 */
public interface Mapper<I, O> {
    /**
     * Map the input value and return the mapped output value.
     *
     * @param input The input value to map
     * @return The mapped value
     * @throws DataMappingException Thrown when the input data couldn't be mapped
     */
    O map(I input) throws DataMappingException;
}
