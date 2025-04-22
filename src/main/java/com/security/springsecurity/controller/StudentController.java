package com.security.springsecurity.controller;

import com.security.springsecurity.entity.Student;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.web.HttpRequestHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class StudentController {

    private List<Student> students = new ArrayList<>(List.of(
            new Student(1, "Adarsh Suman", 64),
            new Student(2, "Shubham Sharma", 78)
    ));

    @GetMapping("/students")
    public List<Student> getStudents(){
        return students;
    }

    @PutMapping("/students")
    public Student addStudent(@RequestBody Student student){
        students.add(student);
        return student;
    }

    @GetMapping("/csrf-token")
    public CsrfToken getCsrfToken(HttpServletRequest servletRequest){
        return (CsrfToken) servletRequest.getAttribute("_csrf");
    }

}
