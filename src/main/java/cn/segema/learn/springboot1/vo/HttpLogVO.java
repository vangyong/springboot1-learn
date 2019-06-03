package cn.segema.learn.springboot1.vo;

import java.io.Serializable;
import java.math.BigInteger;

import io.swagger.annotations.ApiModel;
import lombok.Data;

@ApiModel("http日志VO")
@Data
public class HttpLogVO implements Serializable{

    private static final long serialVersionUID = -468745631337338372L;

    private BigInteger guid;
    
    private String proto;
    private String appProto;
    private String appType;

    private String event_typeA;
    private String event_typeB;
    private String event_description;
    private String source_endpoint_ip;
    private String source_geo_countryCode;
    private String source_geo_city;
    private String destination_endpoint_ip;
    private String destination_geo_countryCode;
    private String destination_geo_city;
    

}
