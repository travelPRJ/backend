package com.iot.travel.config.oauth;

public interface OAuthUserInfo {

    String getProviderId();
    String getProvider();
    String getEmail();
    String getName();
}
