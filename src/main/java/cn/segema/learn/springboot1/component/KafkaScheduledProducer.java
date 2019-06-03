package cn.segema.learn.springboot1.component;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.util.concurrent.ListenableFuture;

import com.alibaba.fastjson.JSON;
import com.google.gson.JsonObject;

import cn.segema.learn.springboot1.vo.HttpLogVO;

//@Component
//@EnableScheduling
//public class KafkaScheduledProducer {
//    
//	@Autowired
//	private KafkaTemplate kafkaTemplate;
//
//	@Scheduled(cron = "*/5 * * * * ?")
//	public void send() {
//		String id = UUID.randomUUID().toString();
//		HttpLogVO csocLog = new HttpLogVO();
//		csocLog.setEvent_typeA("应用程序");
//		csocLog.setEvent_typeB("发现新漏洞");
//		csocLog.setEvent_description("事件描述");
//		csocLog.setSource_endpoint_ip("src_ip1");
//		csocLog.setSource_geo_city("src_geo_city1");
//		csocLog.setSource_geo_countryCode("src_countryCode");
//		csocLog.setDestination_endpoint_ip("dst_ip1");
//		csocLog.setDestination_geo_city("dst_geo_city1");
//		csocLog.setDestination_geo_countryCode("dst_geo_countryCode1");
//		
//		ListenableFuture future = kafkaTemplate.send("test", id,JSON.toJSONString(csocLog));
//		future.addCallback(o -> System.out.println("send-消息发送成功：" + csocLog),
//				throwable -> System.out.println("消息发送失败：" + csocLog));
//	}
//}
