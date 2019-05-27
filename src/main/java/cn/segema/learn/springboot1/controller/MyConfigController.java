package cn.segema.learn.springboot1.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cn.segema.learn.springboot1.config.MyConfig;
import io.swagger.annotations.Api;

@RestController
@RequestMapping("/myconfig")
@Api(value = "配置管理")
public class MyConfigController {

    @Autowired
    MyConfig config;

    @GetMapping("/show")
    public Map getConfig() {
        String version = config.getVersion();
        String name = config.getName();
        Map map = new HashMap();
        map.put("version", version);
        map.put("name", name);
        return map;
    }
}
