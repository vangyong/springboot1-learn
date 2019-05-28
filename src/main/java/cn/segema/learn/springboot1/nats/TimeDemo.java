package cn.segema.learn.springboot1.nats;

import java.time.Duration;

public class TimeDemo {
    public static void main(String[] args) {
        System.out.println(Duration.ofSeconds(2));

        Duration duration = Duration.ofSeconds(1);
        System.out.println(duration.getSeconds());
        
        
        Duration duration1 = duration.minusHours(3);
        System.out.println(duration1.getSeconds());

    }
}
