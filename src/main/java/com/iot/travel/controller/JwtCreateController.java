package com.iot.travel.controller;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.iot.travel.config.jwt.JwtProperties;
import com.iot.travel.config.oauth.GoogleUser;
import com.iot.travel.config.oauth.KaKaoUser;
import com.iot.travel.config.oauth.OAuthUserInfo;
import com.iot.travel.entity.User;
import com.iot.travel.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.Map;

@RestController
@RequiredArgsConstructor
public class JwtCreateController {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @PostMapping("/oauth/jwt/{oauth2}")
    public String jwtCreate(@RequestBody Map<String, Object> data, @PathVariable String oauth2) {
        System.out.println("jwtCreate 실행");
        System.out.println(data);
        OAuthUserInfo oauth2User;

        if(oauth2.equals("google")){
            oauth2User = new GoogleUser(data);
        }else if(oauth2.equals("kakao")){
            oauth2User = new KaKaoUser(data);
        }else {
            return "이런 형태의 oauth2는 지원하지 않음";
        }

        // 해당 정보에 해당하는 유저를 찾음
        User userEntity = userRepository.findByUsername(oauth2User.getProvider() + "_" + oauth2User.getProviderId());

        // 해당 회원이 처음 들어오면 회원가입 진행
        if (userEntity == null) {
            User userRequest = User.builder()
                    .username(oauth2User.getProvider() + "_" + oauth2User.getProviderId())
                    .password(bCryptPasswordEncoder.encode("비밀번호"))
                    .email(oauth2User.getEmail())
                    .provider(oauth2User.getProvider())
                    .providerId(oauth2User.getProviderId())
                    .roles("ROLE_USER")
                    .build();

            userEntity = userRepository.save(userRequest);
        }


        // JWT 생성
        String jwtToken = JWT.create()
                .withSubject(userEntity.getUsername())
                .withExpiresAt(new Date(System.currentTimeMillis() + JwtProperties.EXPIRATION_TIME))
                .withClaim("id", userEntity.getUno())
                .withClaim("nickname",userEntity.getNickname())
                .withClaim("username", userEntity.getUsername())
                .sign(Algorithm.HMAC512(JwtProperties.SECRET));

        // JWT 리턴
        return jwtToken;
    }

}
