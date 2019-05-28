package cn.segema.learn.springboot1.component;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.util.concurrent.ListenableFuture;

import cn.segema.learn.springboot1.vo.HttpLogVO;

@Component
@EnableScheduling
public class ScheduledTest {
	@Autowired
	private KafkaTemplate kafkaTemplate;

	/**
	 * 定时任务 发送kafka消息
	 */
	@Scheduled(cron = "*/5 * * * * ?")
	public void send() {
		String id = UUID.randomUUID().toString();
		HttpLogVO csocLogs = new HttpLogVO();
		csocLogs.setEvent_typeA("应用程序");
		csocLogs.setEvent_typeB("发现新漏洞");
		csocLogs.setEvent_description("事件描述");
		csocLogs.setSource_endpoint_ip("src_ip1");
		csocLogs.setSource_geo_city("src_geo_city1");
		csocLogs.setSource_geo_countryCode("src_countryCode");
		csocLogs.setDestination_endpoint_ip("dst_ip1");
		csocLogs.setDestination_geo_city("dst_geo_city1");
		csocLogs.setDestination_geo_countryCode("dst_geo_countryCode1");
		
		ListenableFuture future = kafkaTemplate.send("test", id,csocLogs);
		future.addCallback(o -> System.out.println("send-消息发送成功：" + csocLogs),
				throwable -> System.out.println("消息发送失败：" + csocLogs));
	}
}
