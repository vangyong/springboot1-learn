package cn.segema.learn.springboot1.repository;

import java.math.BigInteger;
import java.util.List;

import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import cn.segema.learn.springboot1.domain.HttpLog;
import cn.segema.learn.springboot1.vo.HttpLogVO;

@CacheConfig(cacheNames = "httpLogRepository")
@Repository
public interface HttpLogRepository extends JpaRepository<HttpLog, BigInteger> {
    
    @Cacheable(value = "getAllHttpLogs",keyGenerator="keyGenerator2") 
    @Query(value = "SELECT a.* FROM http_log a", nativeQuery = true)
    List<HttpLog> getAllHttpLogs();
    
    @Cacheable(value = "findById",keyGenerator="keyGenerator2")
    @Query(value = "select * from http_log where guid =?1",nativeQuery = true)
    HttpLog findById(BigInteger id);
    
    @Transactional
    @Modifying
    @CacheEvict(value = "getAllHttpLogs",keyGenerator="keyGenerator2",allEntries=true)
    @Query(value = "insert into http_log(guid,app_proto,app_type) values(:#{#httpLog.guid},:#{#httpLog.appProto},:#{#httpLog.appType})",
           nativeQuery = true)
    void createHttpLog(@Param("httpLog") HttpLogVO httpLog);
    
    
    @Transactional
    @Modifying
    @CacheEvict(value = {"getAllHttpLogs"},keyGenerator="keyGenerator2",allEntries=true)
    @Query(value = "update http_log set app_proto=:#{#httpLog.appProto},app_type=:#{#httpLog.appType} where guid=:#{#httpLog.guid}",
           nativeQuery = true)
    void updateHttpLog(@Param("httpLog") HttpLogVO httpLog);
    
    @CacheEvict(value = {"getAllHttpLogs",},keyGenerator="keyGenerator2",allEntries=true)
    @Query(value = "delete from http_log where guid=?1",nativeQuery = true)
    void deleteById(BigInteger id);

}
