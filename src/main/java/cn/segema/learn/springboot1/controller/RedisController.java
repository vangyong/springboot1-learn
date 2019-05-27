package cn.segema.learn.springboot1.controller;

import java.math.BigInteger;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import javax.annotation.Resource;
import org.springframework.data.redis.core.DefaultTypedTuple;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.SetOperations;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.data.redis.core.ZSetOperations.TypedTuple;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cn.segema.learn.springboot1.domain.User;
import io.swagger.annotations.Api;

@RestController
@RequestMapping("/redis")
@Api(value = "redis缓存")
public class RedisController {

//    @Autowired
//    private StringRedisTemplate stringRedisTemplate;

    @Resource 
    private RedisTemplate redisTemplate;

    @GetMapping("/test1/{id}")
    public ResponseEntity test1(@PathVariable BigInteger id) {
        User user = new User();
        user.setUserId(id);
        user.setUserName("uname");
        user.setPassword("password");
//        ValueOperations valueOperations = redisTemplate.opsForValue();
//        String key = User.class.getName()+":"+user.getId();
//        valueOperations.set(key, user);
//        redisTemplate.expire(key, 60, TimeUnit.SECONDS);
//        User vo = (User) valueOperations.get(key);
//        return new ResponseEntity(vo, HttpStatus.OK);
        
//        HashOperations hashOperations = redisTemplate.opsForHash();
//        String key = User.class.getName()+":"+user.getId();
//        Map<String, String> map = new HashMap<String, String>();
//        map.put("cff", "xsg");
//        map.put("cl", "xxsg");
//        hashOperations.putAll("book", map);
//        hashOperations.put(String.valueOf(id), key, user);
//        //redisTemplate.expire(key, 60, TimeUnit.SECONDS);
//        Object hv = hashOperations.get(String.valueOf(id), key);
//        Object book = hashOperations.get("book", "cff");
//        return new ResponseEntity(book, HttpStatus.OK);
        
        
//        ListOperations<String,Object> listOperations = redisTemplate.opsForList();
//        listOperations.leftPush("l1", user);
//        listOperations.leftPush("l2", user);
//        listOperations.rightPush("r1", user);
//        listOperations.rightPush("r2", user);
//        Object l1 = listOperations.leftPop("l1");
//        System.out.println(l1);
//        Object l2 = listOperations.leftPop("l2");
//        System.out.println(l2);
//        Object r1 = listOperations.leftPop("r1");
//        System.out.println(r1);
//        Object r2 = listOperations.leftPop("r2");
//        System.out.println(r2);
//        return new ResponseEntity(l1, HttpStatus.OK);
        
        
//        SetOperations<String,Object> setOperations = redisTemplate.opsForSet();
//        setOperations.add("s1", "set1");
//        setOperations.add("s1", "set2");
//        setOperations.add("s1", "set3");
//        setOperations.add("s2", "set2");
//        setOperations.add("s3", "set3");
//        Set<Object> sets = setOperations.members("s1");
//        //Object s1 = setOperations.pop("s1");
//        return new ResponseEntity(sets, HttpStatus.OK);
        
        
        ZSetOperations<String,Object> zsetOperations = redisTemplate.opsForZSet();
        Set<TypedTuple<Object>> tuples = new HashSet<TypedTuple<Object>>();
        TypedTuple<Object> a = new DefaultTypedTuple<Object>(tuples, (double) 0);
        zsetOperations.add("zk1", "value1", 1);
        zsetOperations.add("zk1", "value2", 2);
        zsetOperations.add("zk1", "value3", 3);
        zsetOperations.add("zk1", "value6", 6);
        zsetOperations.add("zk1", "value7", 7);
        zsetOperations.add("zk1", "value4", 4);
        zsetOperations.add("zk1", "value5", 5);
        Set<Object> zks =  zsetOperations.range("zk1", 1, 3);
        zsetOperations.remove("zk1", "value2");
        return new ResponseEntity(zks, HttpStatus.OK);
        
    }
    
    @GetMapping("/test2/{id}")
    public User test2(@PathVariable BigInteger id) {
        User user = new User();
        user.setUserId(id);
        user.setUserName("uname");
        user.setPassword("password");
        ValueOperations valueOperations = redisTemplate.opsForValue();
        HashOperations hashOperations = redisTemplate.opsForHash();
        ListOperations<String,Object> listOperations = redisTemplate.opsForList();
        SetOperations<String,Object> setOperations = redisTemplate.opsForSet();
        ZSetOperations<String,Object> zsetOperations = redisTemplate.opsForZSet();
        String key = User.class.getName()+":"+user.getUserId();
        valueOperations.set(key, user);
        redisTemplate.expire(key, 60, TimeUnit.SECONDS);
        User vo = (User) valueOperations.get(key);
        System.out.println(vo);
        return vo;
    }
}
