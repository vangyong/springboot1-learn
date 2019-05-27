package cn.segema.learn.springboot1.repository;

import java.util.List;

import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import cn.segema.learn.springboot1.entity.User;

@CacheConfig(cacheNames = "userRepository")
@Repository
public interface UserRepository extends JpaRepository<User, Long>,JpaSpecificationExecutor<User> {

    User findByUsername(String username);

    @Cacheable(value = "getAllUsers",keyGenerator="keyGenerator2") 
    @Query(value = "SELECT a.* FROM tb_user a", nativeQuery = true)
    List<User> getAllUsers();
    
    @Cacheable(key ="#id")
    @Query(value = "select * from user where id =?1",nativeQuery = true)
    User findById(@Param("id") Long id);
    
    @CachePut(key = "#id")
    @Query(value = "insert into tb_user(id,username,password) values(?1,?2,'123456')",nativeQuery = true)
    void createById(@Param("id")Long id,@Param("name")String name);
    
    @CachePut(key = "#id")
    @Query(value = "update tb_user set username=?2 where id=:?1",nativeQuery = true)
    void updataById(Long id,String name);
    
   // @CacheEvict(key ="#id",allEntries=true)
    @Query(value = "delete from tb_user where id=?1",nativeQuery = true)
    void deleteById(Long id);
    
}
