package cn.segema.learn.springboot1.repository;

import java.math.BigInteger;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import cn.segema.learn.springboot1.domain.HttpLog;

@Repository
public interface HttpLogRepository extends JpaRepository<HttpLog, BigInteger> {

}
