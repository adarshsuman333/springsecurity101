package com.security.springsecurity.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.CsrfConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception{
        //Disabling the csrf token
//        httpSecurity.csrf(customizer -> customizer.disable());

        //Setting up authorisation for every request
//        httpSecurity.authorizeHttpRequests(request -> request.anyRequest().authenticated());

        //Enabling form login
//        httpSecurity.formLogin(Customizer.withDefaults());

        //Doesn't output html form in Postman
//        httpSecurity.httpBasic(Customizer.withDefaults());

        //making http stateless
//        httpSecurity.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));

//        return httpSecurity.build();

        //Doing it the imperative way

        //Disabling the csrf
//        Customizer<CsrfConfigurer<HttpSecurity>> csrfConfigurerCustomizer = new Customizer<CsrfConfigurer<HttpSecurity>>() {
//            @Override
//            public void customize(CsrfConfigurer<HttpSecurity> httpSecurityCsrfConfigurer) {
//                httpSecurityCsrfConfigurer.disable();
//            }
//        };
//
//        httpSecurity.csrf(csrfConfigurerCustomizer);

//        httpSecurity.authorizeHttpRequests()

        //Builder pattern
        httpSecurity
                .csrf(customizer -> customizer.disable())
                .authorizeHttpRequests(request -> request.anyRequest().authenticated())
                .formLogin(Customizer.withDefaults())
                .httpBasic(Customizer.withDefaults())
                .sessionManagement(session -> session
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS));
        return httpSecurity.build();
    }
}
