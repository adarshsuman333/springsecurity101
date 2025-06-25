package com.security.springsecurity.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.CsrfConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    private UserDetailsService userDetailsService;

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
        return httpSecurity
                .csrf(customizer -> customizer.disable())
                .authorizeHttpRequests(request -> request
                        .requestMatchers("register", "login")
                        .permitAll()
                        .anyRequest().authenticated())
                .formLogin(Customizer.withDefaults())
                .httpBasic(Customizer.withDefaults())
                .sessionManagement(session -> session
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .build();
    }

//    @Bean
//    public UserDetailsService userDetailsService(){

//        UserDetails user1 = User
//                .withDefaultPasswordEncoder()
//                .username("Kiran")
//                .password("nariK")
//                .roles("USER")
//                .build();
//
//        UserDetails user2 = User
//                .withDefaultPasswordEncoder()
//                .username("Chanda")
//                .password("adnahC")
//                .roles("ADMIN")
//                .build();
//
//        return new InMemoryUserDetailsManager(user1, user2);

//    }

    @Bean
    public AuthenticationProvider authenticationProvider() {

//        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
//        return provider;

        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();

        //We are not using any password encoder, we can use them, but we are not. We are using the default one so, we will see the plain-text.
        provider.setPasswordEncoder(new BCryptPasswordEncoder(15));

        //Next we want to specify before we return the object, is the userDetailsService. UserDetailsService works behind the scenes and is responsible to verify it, and we want to change the authentication provider. It will also use the userDetailsService and we have to specify that.

        //We have created our own UserDetailsService, not the bean which we created. We want Spring to inject the object. So, we have created a userDetailsService at the top and have Autowired it as well. Unfortunately, Spring will have no idea how to provide this or maybe Spring will provide a default one but that's not what we want. We want to customize it. We want to have our own UserDetailsService. How do we create our own UserDetailsService?

        //We know that UserDetailsService is an interface, so, we need to create a class that implements it. That might make our job easy. So, we have to create a class called UserDetailsService or something else as well. But before we do that, we have to sort the classes and interfaces in proper packages. The packages we have in this project so far is config, controller, entity, repository, and service.

        //In the service package, we want to create a class which implements the userDetailsService. This class is named as 'MyUserDetailsService'. This class implements UserDetailsService. In UserDetailsService, we have got a method that we have to implement. This method is loadUserByUsername(). But how will we load the user by username and from where?

        //That's where we have to think about layers. Now, we have a service layer. Service says I want to get some data and the data should be coming from the database. The data will be provided to us by repository. So, that means if we want this to fetch the data, we need a repo layer. We have repo layer and there already exists a repository.

        // In JPA, we define interface for the repo, and it will give us all the methods. So, in the repository layer/package, we have created a UserRepo interface. This repo extends the JpaRepository. And if we want to have repository, we will have to add two dependencies in our pom.xml
        provider.setUserDetailsService(userDetailsService);
        return  provider;
    }

//    @Bean
//    public AuthenticationManager authenticationManager(AuthenticationConfiguration config){
//        return config.getAuthenticationManager();
//    }

}