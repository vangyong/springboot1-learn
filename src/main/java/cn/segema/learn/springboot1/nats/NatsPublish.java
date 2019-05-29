package cn.segema.learn.springboot1.nats;

import java.nio.charset.StandardCharsets;
import java.time.Duration;

import io.nats.client.Connection;
import io.nats.client.Nats;

/**
 * @category Nats发送端
 * @author wangyong
 * @date 2019/05/29
 */
public class NatsPublish {
    public static void main(String[] args){
        
        Connection nc;
        try {
            nc = Nats.connect("nats://localhost:4222");
            nc.publish("subject", "hello world".getBytes(StandardCharsets.UTF_8));
            nc.flush(Duration.ofSeconds(5));
            nc.close();
            System.out.println("sendsucess:");
        } catch (Exception e) {
             e.printStackTrace();
        }
       
        
    }
}
