package com.nhsd.website.provider;

import com.nhsd.website.config.Authentication;
import com.nhsd.website.exception.AuthenticationException;
import com.nhsd.website.model.AccessToken;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AuthenticationProvider {

    private static final Logger log = LoggerFactory.getLogger(AuthenticationProvider.class);

    private static AccessToken accessToken;

    public static String getAccessToken() throws AuthenticationException {
        if (accessToken == null || accessToken.isExpired()) {
            accessToken = Authentication.getUserAccessToken();
            log.info("Stored access token with ID {} in memory", accessToken.getId());
        }

        return accessToken.getToken();
    }
}
