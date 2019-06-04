package cn.segema.learn.springboot1.controller;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;

import cn.segema.learn.springboot1.vo.HttpLogVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(value = "kafka测试")
@RestController
@RequestMapping(value = "/kafka")
public class KafkaProducerController {

    @Autowired
    private KafkaTemplate kafkaTemplate;

    @ApiOperation(value = "发送消息")
    @GetMapping("/producer")
    public ResponseEntity producer() {
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
        String msg = System.currentTimeMillis() + JSON.toJSONString(csocLog);
        for (int i = 1; i <= 2; i++) {
            ListenableFuture future = kafkaTemplate.send("test1", msg);
            future.addCallback(o -> System.out.println("send-消息发送成功：" + msg),
                throwable -> System.out.println("消息发送失败：" + msg));

        }
        return new ResponseEntity("success", HttpStatus.OK);
    }
}
