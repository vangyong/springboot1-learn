package cn.segema.learn.springboot1.nats;

import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.UUID;

import com.alibaba.fastjson.JSON;

import cn.segema.learn.springboot1.vo.HttpLogVO;
import io.nats.client.Connection;
import io.nats.client.Nats;

public class MultThreadPublish {

    public static void main(String[] args) {
        try {
            MultThreadPublish multThreadPublish = new MultThreadPublish();
            for(int i=0;i<1000;i++) {
                MyThread myThread = multThreadPublish.new MyThread();
                myThread.start();
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    class MyThread extends Thread {

        @Override
        public void run() {
            String id = UUID.randomUUID().toString();
            HttpLogVO csocLog = new HttpLogVO();
            csocLog.setEvent_typeA("应用程序");
            csocLog.setEvent_typeB("发现新漏洞");
            csocLog.setEvent_description("事件描述");
            csocLog.setSource_endpoint_ip("src_ip1");
            csocLog.setSource_geo_city("src_geo_city1");
            csocLog.setSource_geo_countryCode("src_countryCode");
            csocLog.setDestination_endpoint_ip("dst_ip1");
            csocLog.setDestination_geo_city("dst_geo_city1");
            csocLog.setDestination_geo_countryCode("dst_geo_countryCode1");
            String msg = System.currentTimeMillis() + JSON.toJSONString(csocLog)+Thread.currentThread().getName();
            Connection nc=null;
            try {
                nc=Nats.connect("nats://10.10.19.53:4222");
                nc.publish("subject", msg.getBytes(StandardCharsets.UTF_8));
                nc.flush(Duration.ofSeconds(5));
                nc.close();
                System.out.println("send success");
            } catch (Exception e) {
                 e.printStackTrace();
            }finally {
                if(nc!=null) {
                    try {
                        nc.close();
                    } catch (InterruptedException e) {
                         e.printStackTrace();
                    }
                }
            } 
            
        }
    }
}
