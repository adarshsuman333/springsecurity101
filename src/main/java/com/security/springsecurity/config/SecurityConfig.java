package com.security.springsecurity.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception{
        //Disabling the csrf token
        httpSecurity.csrf(customizer -> customizer.disable());

        //Setting up authorisation for every request
        httpSecurity.authorizeHttpRequests(request -> request.anyRequest().authenticated());

        //Enabling form login
        httpSecurity.formLogin(Customizer.withDefaults());


        return httpSecurity.build();
    }
}
