package com.security.springsecurity.repository;

import com.security.springsecurity.entity.User;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
//In JpaRepository, we have to pass two things: one is the class which will refer to the table and the primary key. The class that will refer to the table in our case is User and the primary key is Integer.

//When we talk about JPA, we define a model class. So for a table, there will be a table in our database.
public interface UserRepo extends JpaRepository<User, Integer> {

    User findByUsername(String username);
    User registerUser(User user);
}
