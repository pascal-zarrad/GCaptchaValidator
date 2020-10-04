package com.github.playerforcehd.gcaptchavalidator.exception.mapper;

import com.github.playerforcehd.gcaptchavalidator.exception.GCaptchaValidatorException;

/**
 * Exception thrown when some error occurs while mapping data.
 *
 * @author Pascal Zarrad
 * @since 3.0.0
 */
public class DataMappingException extends GCaptchaValidatorException {
    public DataMappingException() {
    }

    public DataMappingException(String message) {
        super(message);
    }

    public DataMappingException(String message, Throwable cause) {
        super(message, cause);
    }

    public DataMappingException(Throwable cause) {
        super(cause);
    }

    public DataMappingException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
