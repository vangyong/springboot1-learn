package cn.segema.learn.springboot1.repository;

import java.math.BigInteger;
import java.util.List;
import java.util.Map;

import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import cn.segema.learn.springboot1.domain.User;
import cn.segema.learn.springboot1.vo.UserVO;

@CacheConfig(cacheNames = "userRepository")
@Repository
public interface UserRepository extends JpaRepository<User, BigInteger>,JpaSpecificationExecutor<User> {

    @Cacheable(value = "getAllUsers",keyGenerator="keyGenerator2") 
    @Query(value = "SELECT a.* FROM tb_user a", nativeQuery = true)
    List<User> getAllUsers();
    
    @Cacheable(value = "findById",keyGenerator="keyGenerator2")
    @Query(value = "select * from tb_user where user_id =?1",nativeQuery = true)
    User findById(BigInteger id);
    
    @Transactional
    @Modifying
    @CacheEvict(value = "getAllUsers",keyGenerator="keyGenerator2",allEntries=true)
    @Query(value = "insert into tb_user(user_id,user_name,password) values(:#{#user.userId},:#{#user.userName},:#{#user.password})",
           nativeQuery = true)
    void createUser(@Param("user") UserVO user);
    
    @Transactional
    @Modifying
    @CacheEvict(value = "getAllUsers",keyGenerator="keyGenerator2",allEntries=true)
    @Query(value = "insert into tb_user(user_id,user_name,password) values(?1,?2,?3)",nativeQuery = true)
    int addUser(BigInteger userId,String userName,String password);
    
    @Transactional
    @Modifying
    @CacheEvict(value = {"findById"},keyGenerator="keyGenerator2",allEntries=true)
    @Query(value = "update tb_user set user_name=:#{#user.userName} where user_id=:#{#user.userId}",nativeQuery = true)
    void updateUser(@Param("user") UserVO user);
    
    @CacheEvict(value = {"getAllUsers",},keyGenerator="keyGenerator2",allEntries=true)
    @Query(value = "delete from tb_user where user_id=?1",nativeQuery = true)
    void deleteById(BigInteger id);
    
    
    @Query(value = "SELECT * FROM tb_user WHERE if(:#{#user.userName}!='',user_name = :#{#user.userName},1=1)  ORDER BY ?#{#pageable}",
               countQuery = "SELECT count(*) FROM tb_user WHERE if(:#{#user.userName}!='',user_name = :#{#user.userName},1=1) ",
               nativeQuery = true)
    public Page<User> findByPage(@Param("user") UserVO user, Pageable pageable);
    
    
//    @Query(value = "SELECT user_id as userId,user_name as userName,password as password FROM tb_user WHERE if(:#{#user.userName}!='',user_name = :#{#user.userName},1=1) ORDER BY ?#{#pageable}",
//            countQuery = "SELECT count(*) FROM tb_user WHERE if(:#{#user.userName}!='',user_name = :#{#user.userName},1=1) ",
//            nativeQuery = true)
//    public Page<UserVO> findByPage(@Param("user") UserVO user, Pageable pageable);
}
