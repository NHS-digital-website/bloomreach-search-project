package com.nhsd.website.model;

import org.jetbrains.annotations.NotNull;

import java.util.Date;

public class AccessToken {

    private final String token;
    private final String id;
    private final Date expirationDate;

    public AccessToken(String token, String id, Date expirationDate) {
        this.token = token;
        this.id = id;
        this.expirationDate = expirationDate;
    }

    public String getToken() {
        return token;
    }

    public String getId() {
        return id;
    }

    public boolean isExpired() {
        return now().after(expirationDate);
    }

    @NotNull
    private Date now() {
        return new Date();
    }
}
