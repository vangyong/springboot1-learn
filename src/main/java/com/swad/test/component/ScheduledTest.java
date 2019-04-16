package com.swad.test.component;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.util.concurrent.ListenableFuture;

@Component
@EnableScheduling
public class ScheduledTest {
	@Autowired
	private KafkaTemplate kafkaTemplate;

	/**
	 * 定时任务 发送kafka消息
	 */
/*	@Scheduled(cron = "00/1 * * * * ?")
	public void send() {
		String id = UUID.randomUUID().toString();
		String message = UUID.randomUUID().toString()+System.currentTimeMillis();
		ListenableFuture future = kafkaTemplate.send("test", id,message);
		future.addCallback(o -> System.out.println("send-消息发送成功：" + message),
				throwable -> System.out.println("消息发送失败：" + message));
	}*/
}
