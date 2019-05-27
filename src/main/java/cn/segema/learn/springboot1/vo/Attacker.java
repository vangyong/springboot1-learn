package cn.segema.learn.springboot1.vo;

import java.math.BigInteger;

public class Attacker {

    private BigInteger guid;

    private String guidStr;

    private BigInteger pluginId;

    private Integer typeId;

    private BigInteger flowId;
    private String flowIdStr;
    private Integer sensorId;
    private BigInteger logTime;
    private String logTimeStr;
    private BigInteger flowTime;
    private Integer ipVer;
    
    
    private Long srcIp;
    private String srcStrIp;
    private Long dstIp;
    private String dstStrIp;
    private Integer srcPort;
    private Integer dstPort;
    private String srcMac;
    private String dstMac;
    private String proto;
    private String appProto;
    private String appType;

}
