package com.security.springsecurity.service;

import com.security.springsecurity.entity.User;
import com.security.springsecurity.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    AuthenticationManager authenticationManager;

    private BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder(15);

    public User registerUser(User user){
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        return userRepo.save(user);
    }

    public String verify(User user) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken( user.getUsername(), user.getPassword() ) );
        if (authentication.isAuthenticated()){
            return "Success";
        }
        else {
            return "Failed";
        }
    }
}
