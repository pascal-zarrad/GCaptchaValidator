package com.github.playerforcehd.gcaptchavalidator.model.response;

import java.util.Date;
import java.util.Map;

/**
 * An immutable implementation of the {@link ValidationResponse}.
 *
 * @author Pascal Zarrad
 * @since 3.0.0
 */
public final class SiteVerifyResponse implements ValidationResponse {
    /**
     * The state if the response was successful
     */
    private final boolean succeeded;

    /**
     * The timestamp when the challenge has been loaded.
     */
    private final Date challengeTimestamp;

    /**
     * The type of client that send the response
     */
    private final ClientType clientType;

    /**
     * The hostname/ip or android package name
     */
    private final String hostnameOrPackageName;

    /**
     * The errors returned by the request (if there are any)
     */
    private final ValidationError[] errors;

    /**
     * Constructor
     *
     * @param succeeded The state if the validation was positive or negative
     * @param challengeTimestamp The timestamp when the challenge has been loaded
     * @param clientType The type of the client that send the validation request
     * @param hostnameOrPackageName The hostname/ip or android package name of the client
     * @param errors The errors returned in the response
     */
    public SiteVerifyResponse(
        boolean succeeded,
        Date challengeTimestamp,
        ClientType clientType,
        String hostnameOrPackageName,
        ValidationError[] errors
    ) {
        this.succeeded = succeeded;
        this.challengeTimestamp = challengeTimestamp;
        this.clientType = clientType;
        this.hostnameOrPackageName = hostnameOrPackageName;
        this.errors = errors;
    }

    @Override
    public boolean hasSucceeded() {
        return this.succeeded;
    }

    @Override
    public Date getChallengeTimestamp() {
        return this.challengeTimestamp;
    }

    @Override
    public ClientType getClientType() {
        return this.clientType;
    }

    @Override
    public String getHostnameOrPackageName() {
        return this.hostnameOrPackageName;
    }

    @Override
    public ValidationError[] getErrors() {
        return this.errors;
    }
}
