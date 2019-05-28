package cn.segema.learn.springboot1.nats;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.concurrent.TimeoutException;

import io.nats.client.Connection;
import io.nats.client.Message;
import io.nats.client.MessageHandler;
import io.nats.client.Nats;
import io.nats.client.Subscription;

public class NatsSubscribe {
    public static void main(String[] args) throws IOException, TimeoutException {

        Connection nc;
        try {
            nc = Nats.connect("nats://localhost:4222");
            Subscription sub = nc.subscribe("subject");
            // nc.flush(Duration.ofSeconds(5));
            Message msg = sub.nextMessage(Duration.ofSeconds(1));
            if (msg != null) {
                System.out.println(new String(msg.getData(), StandardCharsets.UTF_8));
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
