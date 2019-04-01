package com.swad.test.repository;

import java.util.List;

import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.swad.test.entity.User;

@CacheConfig(cacheNames = "userRepository")
@Repository
public interface UserRepository extends JpaRepository<User, Long>,JpaSpecificationExecutor<User> {

    User findByUsername(String username);

    @Cacheable(value = "getAllUsers",keyGenerator="keyGenerator2") 
    @Query(value = "SELECT a.* FROM tb_user a", nativeQuery = true)
    List<User> getAllUsers();
}
