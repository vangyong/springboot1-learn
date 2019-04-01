 package com.swad.test.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.swad.test.entity.User;
import com.swad.test.repository.UserRepository;

/*@Service
@CacheConfig(cacheNames = "userService")*/
public class UserService {
     
/*     @Autowired
     public UserRepository userRepository;
     
     @Cacheable(value = "getAllUsers",keyGenerator="keyGenerator") 
     public List<User> getAllUsers() {
         return userRepository.getAllUsers();
     }
*/
     

}
