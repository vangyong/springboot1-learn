package com.swad.test.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.swad.test.entity.User;
import com.swad.test.repository.UserRepository;

import io.swagger.annotations.Api;

@RestController
@RequestMapping("/user")
@Api(value = "用户管理")
public class UserController {

     @Autowired
     private UserRepository userRepository;

    @GetMapping("/all")
    public List<User> getAllUsers() {
        System.out.println("只有第一次才会打印sql语句");
        return userRepository.getAllUsers();
    }
    
    @GetMapping("/find/{id}")
    public ResponseEntity findUser(@PathVariable Long id) {
        userRepository.findById(id);
        return new ResponseEntity("success",HttpStatus.OK);
    }
    
    @GetMapping("/create")
    public ResponseEntity createUser() {
    	User user = new User();
    	user.setId(100L);
    	user.setUsername("100name");
    	user.setPassword("100pass");
         userRepository.createById(100L, "hhhh");
         return new ResponseEntity(user,HttpStatus.OK);
    }
    
    @GetMapping("/delete/{id}")
    public ResponseEntity deleteUser(@PathVariable Long id) {
        userRepository.deleteById(id);
        return new ResponseEntity("success",HttpStatus.OK);
    }
    
    @GetMapping("/update")
    public ResponseEntity updateUser() {
    	User user = new User();
    	user.setId(100L);
    	user.setUsername("100name");
    	user.setPassword("100pass");
        userRepository.updataById(100L,"new100name");
        
        return new ResponseEntity(user,HttpStatus.OK);
    }
}
