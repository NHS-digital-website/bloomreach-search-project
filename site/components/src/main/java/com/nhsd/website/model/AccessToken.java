package com.nhsd.website.model;

import java.time.LocalDateTime;

public class AccessToken {

    private final String token;
    private final String refreshToken;
    private final LocalDateTime expirationDate;

    public AccessToken(String token, String refreshToken, int expirationSeconds) {
        this.token = token;
        this.refreshToken = refreshToken;
        this.expirationDate = LocalDateTime.now().plusSeconds(expirationSeconds);
    }

    public String getToken() {
        return token;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public boolean isExpired() {
        return LocalDateTime.now().isAfter(expirationDate);
    }
}
