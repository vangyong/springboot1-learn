package com.swad.test.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.swad.test.entity.User;
import com.swad.test.repository.UserRepository;
import com.swad.test.service.UserService;

import io.swagger.annotations.Api;

@RestController
@RequestMapping("/user")
@Api(value = "用户管理")
public class UserController {

     @Autowired
     private UserRepository userRepository;

/*    @Autowired
    private UserService userService;*/

    @GetMapping("/all")
    public List<User> getAllUsers() {
        System.out.println("只有第一次才会打印sql语句");
        return userRepository.getAllUsers();
    }
}
