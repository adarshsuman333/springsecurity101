package com.security.springsecurity.service;

import com.security.springsecurity.entity.User;
import com.security.springsecurity.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepo userRepo;

    public User registerUser(User user){
        return userRepo.save(user);
    }
}
