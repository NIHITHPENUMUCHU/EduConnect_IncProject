package com.edutech.progressive.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
public class SecurityConfig {


    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http
            .csrf(csrf -> csrf.disable())
            .authorizeHttpRequests(auth -> auth
                // ✅ JWT endpoints open
                .requestMatchers(new AntPathRequestMatcher("/auth/**")).permitAll()

                // ✅ Student endpoints must be PUBLIC
                .requestMatchers(new AntPathRequestMatcher("/student/**")).permitAll()

                // ✅ Teacher & Course must be PROTECTED
                .requestMatchers(new AntPathRequestMatcher("/teacher/**")).authenticated()
                .requestMatchers(new AntPathRequestMatcher("/course/**")).authenticated()

                .anyRequest().permitAll()
            )
            // ✅ Basic auth only (no DB auth)
            .httpBasic(Customizer.withDefaults());

        return http.build();
    }
}