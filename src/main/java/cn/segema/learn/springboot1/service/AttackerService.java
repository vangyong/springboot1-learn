package cn.segema.learn.springboot1.service;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import javax.annotation.Resource;

import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import cn.segema.learn.springboot1.constant.CommonConstant;
import cn.segema.learn.springboot1.vo.CsocLogs;


@Service
public class AttackerService {
    
    @Resource 
    private RedisTemplate redisTemplate;
    
    public void putAttackerStatistics(CsocLogs csocLogs) {
     
        
    }
    
    public void putAttackerList(CsocLogs csocLogs) {
        Map attackerMap = new HashMap();
        attackerMap.put("src_ip", csocLogs.getSource_endpoint_ip());
        attackerMap.put("src_geo_city", csocLogs.getSource_geo_city());
        attackerMap.put("src_countryCode", csocLogs.getSource_geo_countryCode());
        attackerMap.put("des_ip", csocLogs.getDestination_endpoint_ip());
        attackerMap.put("des_geo_city", csocLogs.getDestination_geo_city());
        attackerMap.put("des_countryCode", csocLogs.getDestination_geo_countryCode());
        
        //最近24小时的数据
        String key24h = CommonConstant.ATTACKER_LIST+":"+"24h";
        HashOperations hashOperations = redisTemplate.opsForHash();
        Map key24hAttacker = (Map)hashOperations.get(key24h, attackerMap.get("src_ip"));
        if(key24hAttacker!=null) {
            key24hAttacker.put("logs_count", Integer.valueOf(((Map)key24hAttacker).get("logs_count").toString()).intValue()+1);
            hashOperations.put(key24h, attackerMap.get("src_ip"), attackerMap);
        }else {
            hashOperations.put(key24h,attackerMap.get("src_ip"), attackerMap); 
        }
        redisTemplate.expire(key24h, 24, TimeUnit.HOURS);
        
        //30天的数据
        String key7d = CommonConstant.ATTACKER_LIST+":"+"7d";
        Map key7dAttacker = (Map)hashOperations.get(key24h, attackerMap.get("src_ip"));
        if(key7dAttacker!=null) {
            key7dAttacker.put("logs_count", Integer.valueOf(((Map)key7dAttacker).get("logs_count").toString()).intValue()+1);
            hashOperations.put(key24h, attackerMap.get("src_ip"), attackerMap);
        }else {
            hashOperations.put(key24h,attackerMap.get("src_ip"), attackerMap); 
        }
        redisTemplate.expire(key7d, 7, TimeUnit.DAYS);
        
        //30天的数据
        String key30d = CommonConstant.ATTACKER_LIST+":"+"30d";
        Map key30dAttacker = (Map)hashOperations.get(key24h, attackerMap.get("src_ip"));
        if(key30dAttacker!=null) {
            key30dAttacker.put("logs_count", Integer.valueOf(((Map)key30dAttacker).get("logs_count").toString()).intValue()+1);
            hashOperations.put(key24h, attackerMap.get("src_ip"), attackerMap);
        }else {
            hashOperations.put(key24h,attackerMap.get("src_ip"), attackerMap); 
        }
        redisTemplate.expire(key30d, 30, TimeUnit.DAYS);
        
    }
}
