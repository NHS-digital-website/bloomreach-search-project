package com.nhsd.website.provider;

import com.microsoft.graph.authentication.IAuthenticationProvider;
import com.microsoft.graph.http.IHttpRequest;

public class HippoAuthenticationProvider implements IAuthenticationProvider {

    private final String accessToken;

    public HippoAuthenticationProvider(String accessToken) {
        this.accessToken = accessToken;
    }

    @Override
    public void authenticateRequest(IHttpRequest request) {
        request.addHeader("Authorization", "Bearer " + accessToken);
    }
}
