package com.github.playerforcehd.gcaptchavalidator.exception;

/**
 * Parent exception of all exceptions that are provided by the library.
 *
 * @author Pascal Zarrad
 * @since 3.0.0
 */
public class GCaptchaValidatorException extends Exception {
    public GCaptchaValidatorException() { }

    public GCaptchaValidatorException(String message) {
        super(message);
    }

    public GCaptchaValidatorException(String message, Throwable cause) {
        super(message, cause);
    }

    public GCaptchaValidatorException(Throwable cause) {
        super(cause);
    }

    public GCaptchaValidatorException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
