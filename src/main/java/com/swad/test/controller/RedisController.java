package com.swad.test.controller;

import java.util.concurrent.TimeUnit;

import javax.annotation.Resource;

import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.SetOperations;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.swad.test.entity.User;

import io.swagger.annotations.Api;

@RestController
@RequestMapping("/redis")
@Api(value = "redis缓存")
public class RedisController {

//    @Autowired
//    private StringRedisTemplate stringRedisTemplate;

    @Resource 
    private RedisTemplate redisTemplate;

    @GetMapping("/test1")
    public User test1() {
        User user = new User();
        user.setId(1L);
        user.setUsername("uname");
        user.setPassword("password");
        ValueOperations valueOperations = redisTemplate.opsForValue();
        HashOperations hashOperations = redisTemplate.opsForHash();
        ListOperations<String,Object> listOperations = redisTemplate.opsForList();
        SetOperations<String,Object> setOperations = redisTemplate.opsForSet();
        ZSetOperations<String,Object> zsetOperations = redisTemplate.opsForZSet();
        String key = User.class.getName()+":"+user.getId();
        valueOperations.set(key, user);
        redisTemplate.expire(key, 60, TimeUnit.SECONDS);
        User vo = (User) valueOperations.get(key);
        System.out.println(vo);
        return vo;
    }
    
    @GetMapping("/test2/{id}")
    public User test2(@PathVariable Long id) {
        User user = new User();
        user.setId(id);
        user.setUsername("uname");
        user.setPassword("password");
        ValueOperations valueOperations = redisTemplate.opsForValue();
        HashOperations hashOperations = redisTemplate.opsForHash();
        ListOperations<String,Object> listOperations = redisTemplate.opsForList();
        SetOperations<String,Object> setOperations = redisTemplate.opsForSet();
        ZSetOperations<String,Object> zsetOperations = redisTemplate.opsForZSet();
        String key = User.class.getName()+":"+user.getId();
        valueOperations.set(key, user);
        redisTemplate.expire(key, 60, TimeUnit.SECONDS);
        User vo = (User) valueOperations.get(key);
        System.out.println(vo);
        return vo;
    }
}
