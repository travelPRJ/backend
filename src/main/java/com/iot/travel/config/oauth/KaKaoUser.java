package com.iot.travel.config.oauth;

import java.util.Map;

public class KaKaoUser implements OAuthUserInfo {

    private Map<String, Object> attribute;

    public KaKaoUser(Map<String,Object> attribute){
        this.attribute = attribute;
    }
    @Override
    public String getProviderId() {
        return (String) attribute.get("aud");
    }

    @Override
    public String getProvider() {
        return "kakao";
    }

    @Override
    public String getEmail() {
        return (String) attribute.get("email");
    }

    @Override
    public String getName() {
        return (String) attribute.get("name");
    }
}
