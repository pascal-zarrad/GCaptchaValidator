package com.github.playerforcehd.gcaptchavalidator.reflect.mapper.response;

import com.github.playerforcehd.gcaptchavalidator.exception.mapper.DataMappingException;
import com.github.playerforcehd.gcaptchavalidator.model.response.ClientType;
import com.github.playerforcehd.gcaptchavalidator.reflect.mapper.Mapper;

import java.util.HashMap;
import java.util.Map;

/**
 * Mapper that maps the raw data from a response to the format used by GCaptchaValidator
 * to make working with the responses easier.
 *
 * This mapper is singleton and cannot be instantiated multiple times.
 *
 * @author Pascal Zarrad
 * @since 3.0.0
 */
public class ValidationResponseMapper implements Mapper<Map<String, Object>, Map<String, Object>> {
    /**
     * Name of the success field in the response
     */
    private static final String SUCCESS_RESPONSE_FIELD = "success";

    /**
     * Name of the mapped success field
     */
    private static final String SUCCESS_RESPONSE_MAPPING = "succeeded";

    /**
     * Name of the challenge_ts field in the response
     */
    private static final String CHALLENGE_TS_RESPONSE_FIELD = "challenge_ts";

    /**
     * Name of the mapped challenge_ts field
     */
    private static final String CHALLENGE_TS_RESPONSE_MAPPING = "challengeTimestamp";

    /**
     * The name of the hostname field in the response
     */
    private static final String HOSTNAME_RESPONSE_FIELD = "hostname";

    /**
     * The name of the client type field
     */
    private static final String CLIENT_TYPE_MAPPING = "clientType";

    /**
     * The name of the field where the hostname or apk_package_name is mapped to
     */
    private static final String SOURCE_FIELD_MAPPING = "hostnameOrPackageName";

    /**
     * The name of the apk_package_field in the response
     */
    private static final String APK_RESPONSE_FIELD = "apk_package_name";

    /**
     * The name of the errors field in the response
     */
    private static final String ERRORS_RESPONSE_FIELD = "errors";

    /**
     * The name of the mapped errors field
     */
    private static final String ERRORS_MAPPING_FIELD = "errors";

    /**
     * The fields that are standard fields and can be mapped
     */
    private static final String[] KNOWN_RESPONSE_FIELDS = {
        SUCCESS_RESPONSE_FIELD,
        CHALLENGE_TS_RESPONSE_FIELD,
        HOSTNAME_RESPONSE_FIELD,
        APK_RESPONSE_FIELD,
        ERRORS_RESPONSE_FIELD
    };

    /**
     * The name of the additional data field where unknown data is put to
     */
    private static final String ADDITIONAL_DATA_MAPPING = "additionalData";

    /**
     * The singleton instance of the {@link ValidationResponseMapper}
     */
    private static ValidationResponseMapper INSTANCE;

    /**
     * Get the {@link ValidationResponseMapper} instance.
     *
     * @return The {@link ValidationResponseMapper} instance
     */
    public static ValidationResponseMapper getInstance() {
        ValidationResponseMapper currentInstance = ValidationResponseMapper.INSTANCE;
        if (currentInstance == null) {
            synchronized (ValidationResponseMapper.class) {
                currentInstance = INSTANCE;
                if (currentInstance == null) {
                    ValidationResponseMapper.INSTANCE = new ValidationResponseMapper();
                }
            }
        }

        return ValidationResponseMapper.INSTANCE;
    }

    /**
     * Constructor
     */
    private ValidationResponseMapper() {}

    @Override
    public Map<String, Object> map(Map<String, Object> input) throws DataMappingException {
        Map<String, Object> output = new HashMap<>();

        // Mapping success - succeeded
        this.mapSuccess(input, output);

        // Mapping challengeTimestamp
        this.mapChallengeTS(input, output);

        // Mapping for hostname and apk_package_name
        this.mapHostnameAndPackageName(input, output);

        // Mapping for errors
        this.mapErrors(input, output);

        // Put unknown fields into additional data
        this.mapAdditionalData(input, output);

        return output;
    }

    /**
     * Map the success field of a response to the mapped one
     *
     * @param input The input to map
     * @param output The mapped output
     */
    private void mapSuccess(Map<String, Object> input, Map<String, Object> output) {
        if (input.containsKey(ValidationResponseMapper.SUCCESS_RESPONSE_FIELD)) {
            output.put(
                ValidationResponseMapper.SUCCESS_RESPONSE_MAPPING,
                input.get(ValidationResponseMapper.SUCCESS_RESPONSE_FIELD)
            );
        }
    }

    /**
     * Map the challenge_ts field of a response to the mapped one
     *
     * @param input The input to map
     * @param output The mapped output
     */
    private void mapChallengeTS(Map<String, Object> input, Map<String, Object> output) {
        if (input.containsKey(ValidationResponseMapper.CHALLENGE_TS_RESPONSE_FIELD)) {
            output.put(
                ValidationResponseMapper.CHALLENGE_TS_RESPONSE_MAPPING,
                input.get(ValidationResponseMapper.CHALLENGE_TS_RESPONSE_FIELD)
            );
        }
    }

    /**
     * Map hostname / apk_package_name to the mapped ones
     *
     * @param input The input to map
     * @param output The mapped output
     */
    private void mapHostnameAndPackageName(Map<String, Object> input, Map<String, Object> output)
        throws DataMappingException {
        if (input.containsKey(ValidationResponseMapper.HOSTNAME_RESPONSE_FIELD)
            && input.containsKey(ValidationResponseMapper.APK_RESPONSE_FIELD)) {
            throw new DataMappingException("Cannot response with both hostname and apk_package_name defined!");
        }

        if (input.containsKey(HOSTNAME_RESPONSE_FIELD)) {
            output.put(ValidationResponseMapper.CLIENT_TYPE_MAPPING, ClientType.WEB);
            output.put(
                ValidationResponseMapper.SOURCE_FIELD_MAPPING,
                input.get(ValidationResponseMapper.HOSTNAME_RESPONSE_FIELD)
            );
        }

        if (input.containsKey(ValidationResponseMapper.APK_RESPONSE_FIELD)) {
            output.put(ValidationResponseMapper.CLIENT_TYPE_MAPPING, ClientType.ANDROID);
            output.put(
                ValidationResponseMapper.SOURCE_FIELD_MAPPING,
                input.get(ValidationResponseMapper.APK_RESPONSE_FIELD)
            );
        }
    }

    /**
     * Map errors field of a response to the mapped one
     *
     * @param input The input to map
     * @param output The mapped output
     */
    private void mapErrors(Map<String, Object> input, Map<String, Object> output) {
        if (input.containsKey(ValidationResponseMapper.ERRORS_RESPONSE_FIELD)) {
            output.put(
                ValidationResponseMapper.ERRORS_MAPPING_FIELD,
                input.get(ValidationResponseMapper.ERRORS_RESPONSE_FIELD)
            );
        }
    }

    /**
     * Map all leftover keys to the additional data map
     *
     * @param input The input to map
     * @param output The output to map
     */
    private void mapAdditionalData(Map<String, Object> input, Map<String, Object> output) {
        Map<String, Object> additionalData = new HashMap<>();
        input.forEach((key, value) -> {
            for (String knownResponseField : ValidationResponseMapper.KNOWN_RESPONSE_FIELDS) {
                if (knownResponseField.equals(key)) {
                    return;
                }
                additionalData.put(key, value);
            }
        });
        output.put(ValidationResponseMapper.ADDITIONAL_DATA_MAPPING, additionalData);
    }
}
