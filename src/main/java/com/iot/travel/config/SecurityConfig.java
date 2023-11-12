package com.iot.travel.config;


import com.iot.travel.config.jwt.JwtAuthenticationFilter;
import com.iot.travel.config.jwt.JwtAuthorizationFilter;
import com.iot.travel.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CorsConfig corsConfig;

    @Bean
    AuthenticationManager authenticationManager(
            AuthenticationConfiguration authenticationConfiguration
    ) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http
                .addFilter(corsConfig.corsFilter())
                .csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS) // 세션 사용 안함
                .and()
                .formLogin().disable() // formLogin 사용안함 ex) security 고정 로그인 페이지 사용 x
                .httpBasic().disable() // httpBasic 방법 사용 안함
                .addFilter(new JwtAuthenticationFilter(authenticationManager((AuthenticationConfiguration) http.getSharedObject(AuthenticationManager.class))))
                .addFilter(new JwtAuthorizationFilter(authenticationManager((AuthenticationConfiguration) http.getSharedObject(AuthenticationManager.class)),userRepository))
                .authorizeRequests()
                .antMatchers("/user/**")
                .access("hasRole('ROLE_USER')")
                .anyRequest().permitAll();

        return http.build();
    }

}
