package cn.segema.learn.springboot1.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;

@Api(value = "nats消息")
@RestController
@RequestMapping(value = "/nats")
public class NatsProducerController {

    @GetMapping(value = "/producer")
    public ResponseEntity producer(){
        return new ResponseEntity("success", HttpStatus.OK);
    }
}
