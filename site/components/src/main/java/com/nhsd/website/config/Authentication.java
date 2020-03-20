package com.nhsd.website.config;

import com.microsoft.aad.msal4j.DeviceCode;
import com.microsoft.aad.msal4j.DeviceCodeFlowParameters;
import com.microsoft.aad.msal4j.IAuthenticationResult;
import com.microsoft.aad.msal4j.PublicClientApplication;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

import java.net.MalformedURLException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.function.Consumer;

public class Authentication {

    private static final String applicationId = "8b6b231a-fea8-4fbf-8c06-02852f3457fb";
    private static final List<String> applicationScopes = Arrays.asList("User.Read", "Calendars.Read");
    private static final String authority = "https://login.microsoftonline.com/common/";
    private static final Logger log = LoggerFactory.getLogger(Authentication.class);

    public static String getUserAccessToken() {
        if (StringUtils.isEmpty(applicationId)) {
            log.error("Authentication not initialized!");
            return null;
        }

        final Set<String> scopeSet = new HashSet<>(applicationScopes);

        PublicClientApplication application;

        try {
            application = PublicClientApplication.builder(applicationId)
                .authority(authority)
                .build();
        } catch (final MalformedURLException exception) {
            return null;
        }

        final Consumer<DeviceCode> deviceCodeConsumer = deviceCode -> log.info(deviceCode.message());

        final IAuthenticationResult result = application.acquireToken(
            DeviceCodeFlowParameters
                .builder(scopeSet, deviceCodeConsumer)
                .build()
        ).exceptionally(throwable -> {
            log.error("Unable to authenticate - {}", throwable.getMessage());
            return null;
        }).join();

        return result == null ? null : result.accessToken();
    }
}
