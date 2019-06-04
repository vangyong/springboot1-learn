package cn.segema.learn.springboot1.component;

import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.UUID;

import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSON;

import cn.segema.learn.springboot1.vo.HttpLogVO;
import io.nats.client.Connection;
import io.nats.client.Nats;

/**
 * @category Nats消息发送端
 * @author wangyong
 * @date 2019/05/29
 */
//@Component
//@EnableScheduling
//public class NatsScheduledPublish {
//
//    @Scheduled(cron = "*/1 * * * * ?")
//    public void send() {
//        try {
//
//            String id = UUID.randomUUID().toString();
//            HttpLogVO csocLog = new HttpLogVO();
//            csocLog.setEvent_typeA("应用程序");
//            csocLog.setEvent_typeB("发现新漏洞");
//            csocLog.setEvent_description("事件描述");
//            csocLog.setSource_endpoint_ip("src_ip1");
//            csocLog.setSource_geo_city("src_geo_city1");
//            csocLog.setSource_geo_countryCode("src_countryCode");
//            csocLog.setDestination_endpoint_ip("dst_ip1");
//            csocLog.setDestination_geo_city("dst_geo_city1");
//            csocLog.setDestination_geo_countryCode("dst_geo_countryCode1");
//
//            Connection nc = Nats.connect("nats://10.10.19.53:4222");
//            String msg = System.currentTimeMillis() + JSON.toJSONString(csocLog);
//            nc.publish("subject", msg.getBytes(StandardCharsets.UTF_8));
//            nc.flush(Duration.ofSeconds(1));
//            nc.close();
//            System.out.println(System.currentTimeMillis()+" sendsucess:" + msg);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//}
