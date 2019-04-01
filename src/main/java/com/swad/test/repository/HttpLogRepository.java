package com.swad.test.repository;

import java.math.BigInteger;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.swad.test.entity.HttpLog;

@Repository
public interface HttpLogRepository extends JpaRepository<HttpLog, BigInteger> {

}
