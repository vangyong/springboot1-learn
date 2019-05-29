package cn.segema.learn.springboot1.nats;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.concurrent.TimeoutException;

import io.nats.client.Connection;
import io.nats.client.Dispatcher;
import io.nats.client.Message;
import io.nats.client.MessageHandler;
import io.nats.client.Nats;
import io.nats.client.Subscription;

/**
 * @category Nats消费端
 * @author wangyong
 * @date 2019/05/29
 */
public class NatsSubscribe {
    public static void main(String[] args) throws IOException, TimeoutException {

        Connection nc;
        try {
            nc = Nats.connect("nats://localhost:4222");
            Dispatcher d = nc.createDispatcher((msg) -> {
                String response = new String(msg.getData(), StandardCharsets.UTF_8);
                System.out.println("response:");
                System.out.println(response);
            });
            d.subscribe("subject");

        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
