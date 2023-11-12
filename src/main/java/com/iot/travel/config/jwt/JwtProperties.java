package com.iot.travel.config.jwt;

public interface JwtProperties {

    String SECRET = "비밀번호"; // 서버만 알고 있는 비밀 값

    int EXPIRATION_TIME = 1000*60*60*24*3; // 3일

    String TOKEN_PREFIX = "Bearer ";

    String HEADER_STRING = "Authorization";
}
