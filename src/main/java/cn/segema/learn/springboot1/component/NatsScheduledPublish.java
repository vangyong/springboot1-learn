package cn.segema.learn.springboot1.component;

import java.nio.charset.StandardCharsets;
import java.time.Duration;

import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import io.nats.client.Connection;
import io.nats.client.Nats;


/**
 * @category Nats消息发送端
 * @author wangyong
 * @date 2019/05/29
 */
@Component
@EnableScheduling
public class NatsScheduledPublish {

	@Scheduled(cron = "*/1 * * * * ?")
	public void send() {
        try {
            Connection nc = Nats.connect("nats://localhost:4222");
            String msg = System.currentTimeMillis()+"hello world";
            nc.publish("subject", msg.getBytes(StandardCharsets.UTF_8));
            nc.flush(Duration.ofSeconds(1));
            nc.close();
            System.out.println("sendsucess:"+msg);
        } catch (Exception e) {
             e.printStackTrace();
        }
	}
}
