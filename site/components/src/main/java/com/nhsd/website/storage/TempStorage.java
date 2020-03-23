package com.nhsd.website.storage;

import com.nhsd.website.model.AccessToken;

public class TempStorage {

    private static AccessToken accessToken;

    public static void setAccessToken(final AccessToken accessToken) {
        TempStorage.accessToken = accessToken;
    }

    public static AccessToken getAccessToken() {
        return TempStorage.accessToken;
    }
}
