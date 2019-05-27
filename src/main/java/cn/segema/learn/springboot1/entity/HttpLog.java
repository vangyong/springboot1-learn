package cn.segema.learn.springboot1.entity;

import java.io.Serializable;
import java.math.BigInteger;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 告警级别
 */
@Entity
@Table(name = "HTTP_LOG")
public class HttpLog implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "guid")
    private BigInteger guid;
    
    private String guidStr;

    @Column(name = "plugin_id")
    private BigInteger pluginId;
    
    @Column(name = "type_id")
    private Integer typeId;
    
    @Column(name = "flow_id")
    private BigInteger flowId;
    private String flowIdStr;
    @Column(name = "sensor_id")
    private Integer sensorId;
    @Column(name = "log_time")
    private BigInteger logTime;
    private String logTimeStr;
    @Column(name = "flow_time")
    private BigInteger flowTime;
    @Column(name = "ip_ver")
    private Integer ipVer;
    @Column(name = "src_ip")
    private Long srcIp;
    private String srcStrIp;
    @Column(name = "dst_ip")
    private Long dstIp;
    private String dstStrIp;
    @Column(name = "src_port")
    private Integer srcPort;
    @Column(name = "dst_port")
    private Integer dstPort;
    @Column(name = "src_mac")
    private String srcMac;
    @Column(name = "dst_mac")
    private String dstMac;
    @Column(name = "proto")
    private String proto;
    @Column(name = "app_proto")
    private String appProto;
    @Column(name = "app_type")
    private String appType;

    @Column(name = "s_iso_code")
    private String sIsoCode;
    @Column(name = "s_country")
    private String sCountry;
    @Column(name = "s_city")
    private String sCity;
    @Column(name = "d_iso_code")
    private String dIsoCode;
    @Column(name = "d_country")
    private String dCountry;
    @Column(name = "d_city")
    private String dCity;

    @Column(name = "method")
    private String method;
    @Column(name = "url")
    private String url;
    @Column(name = "uri")
    private String uri;
    @Column(name = "host")
    private String host;
    @Column(name = "u_agnet")
    private String uAgnet;
    @Column(name = "req_c_type")
    private String reqCType;
    @Column(name = "referrer")
    private String referrer;

    @Column(name = "_post_data")
    private String postData;
    @Column(name = "server")
    private String server;
    @Column(name = "res_c_type")
    private String resCType;
    @Column(name = "x_cache")
    private String xCache;

    @Column(name = "via")
    private String via;
    @Column(name = "x_powered_by")
    private String xPoweredBy;
    @Column(name = "download_fileid")
    private String downloadFileId;
    @Column(name = "code")
    private BigInteger code;

    @Column(name = "files.file_uuid")
    private String[] fileUuid;
    @Column(name = "files.file_name")
    private String[] fileName;
    @Column(name = "files.file_len")
    private BigInteger[] fileLen;
    @Column(name = "files.file_type")
    private String[] fileType;

    @Column(name = "log_date")
    private String logDate;

    public BigInteger getGuid() {
        return guid;
    }

    public void setGuid(BigInteger guid) {
        this.guid = guid;
    }

    public String getGuidStr() {
        return guidStr;
    }

    public void setGuidStr(String guidStr) {
        this.guidStr = guidStr;
    }

    public BigInteger getPluginId() {
        return pluginId;
    }

    public void setPluginId(BigInteger pluginId) {
        this.pluginId = pluginId;
    }

    public Integer getTypeId() {
        return typeId;
    }

    public void setTypeId(Integer typeId) {
        this.typeId = typeId;
    }

    public BigInteger getFlowId() {
        return flowId;
    }

    public void setFlowId(BigInteger flowId) {
        this.flowId = flowId;
    }

    public String getFlowIdStr() {
        return flowIdStr;
    }

    public void setFlowIdStr(String flowIdStr) {
        this.flowIdStr = flowIdStr;
    }

    public Integer getSensorId() {
        return sensorId;
    }

    public void setSensorId(Integer sensorId) {
        this.sensorId = sensorId;
    }

    public BigInteger getLogTime() {
        return logTime;
    }

    public void setLogTime(BigInteger logTime) {
        this.logTime = logTime;
    }

    public String getLogTimeStr() {
        return logTimeStr;
    }

    public void setLogTimeStr(String logTimeStr) {
        this.logTimeStr = logTimeStr;
    }

    public BigInteger getFlowTime() {
        return flowTime;
    }

    public void setFlowTime(BigInteger flowTime) {
        this.flowTime = flowTime;
    }

    public Integer getIpVer() {
        return ipVer;
    }

    public void setIpVer(Integer ipVer) {
        this.ipVer = ipVer;
    }

    public Long getSrcIp() {
        return srcIp;
    }

    public void setSrcIp(Long srcIp) {
        this.srcIp = srcIp;
    }

    public String getSrcStrIp() {
        return srcStrIp;
    }

    public void setSrcStrIp(String srcStrIp) {
        this.srcStrIp = srcStrIp;
    }

    public Long getDstIp() {
        return dstIp;
    }

    public void setDstIp(Long dstIp) {
        this.dstIp = dstIp;
    }

    public String getDstStrIp() {
        return dstStrIp;
    }

    public void setDstStrIp(String dstStrIp) {
        this.dstStrIp = dstStrIp;
    }

    public Integer getSrcPort() {
        return srcPort;
    }

    public void setSrcPort(Integer srcPort) {
        this.srcPort = srcPort;
    }

    public Integer getDstPort() {
        return dstPort;
    }

    public void setDstPort(Integer dstPort) {
        this.dstPort = dstPort;
    }

    public String getSrcMac() {
        return srcMac;
    }

    public void setSrcMac(String srcMac) {
        this.srcMac = srcMac;
    }

    public String getDstMac() {
        return dstMac;
    }

    public void setDstMac(String dstMac) {
        this.dstMac = dstMac;
    }

    public String getProto() {
        return proto;
    }

    public void setProto(String proto) {
        this.proto = proto;
    }

    public String getAppProto() {
        return appProto;
    }

    public void setAppProto(String appProto) {
        this.appProto = appProto;
    }

    public String getAppType() {
        return appType;
    }

    public void setAppType(String appType) {
        this.appType = appType;
    }

    public String getsIsoCode() {
        return sIsoCode;
    }

    public void setsIsoCode(String sIsoCode) {
        this.sIsoCode = sIsoCode;
    }

    public String getsCountry() {
        return sCountry;
    }

    public void setsCountry(String sCountry) {
        this.sCountry = sCountry;
    }

    public String getsCity() {
        return sCity;
    }

    public void setsCity(String sCity) {
        this.sCity = sCity;
    }

    public String getdIsoCode() {
        return dIsoCode;
    }

    public void setdIsoCode(String dIsoCode) {
        this.dIsoCode = dIsoCode;
    }

    public String getdCountry() {
        return dCountry;
    }

    public void setdCountry(String dCountry) {
        this.dCountry = dCountry;
    }

    public String getdCity() {
        return dCity;
    }

    public void setdCity(String dCity) {
        this.dCity = dCity;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public String getuAgnet() {
        return uAgnet;
    }

    public void setuAgnet(String uAgnet) {
        this.uAgnet = uAgnet;
    }

    public String getReqCType() {
        return reqCType;
    }

    public void setReqCType(String reqCType) {
        this.reqCType = reqCType;
    }

    public String getReferrer() {
        return referrer;
    }

    public void setReferrer(String referrer) {
        this.referrer = referrer;
    }

    public String getPostData() {
        return postData;
    }

    public void setPostData(String postData) {
        this.postData = postData;
    }

    public String getServer() {
        return server;
    }

    public void setServer(String server) {
        this.server = server;
    }

    public String getResCType() {
        return resCType;
    }

    public void setResCType(String resCType) {
        this.resCType = resCType;
    }

    public String getxCache() {
        return xCache;
    }

    public void setxCache(String xCache) {
        this.xCache = xCache;
    }

    public String getVia() {
        return via;
    }

    public void setVia(String via) {
        this.via = via;
    }

    public String getxPoweredBy() {
        return xPoweredBy;
    }

    public void setxPoweredBy(String xPoweredBy) {
        this.xPoweredBy = xPoweredBy;
    }

    public String getDownloadFileId() {
        return downloadFileId;
    }

    public void setDownloadFileId(String downloadFileId) {
        this.downloadFileId = downloadFileId;
    }

    public BigInteger getCode() {
        return code;
    }

    public void setCode(BigInteger code) {
        this.code = code;
    }

    public String[] getFileUuid() {
        return fileUuid;
    }

    public void setFileUuid(String[] fileUuid) {
        this.fileUuid = fileUuid;
    }

    public String[] getFileName() {
        return fileName;
    }

    public void setFileName(String[] fileName) {
        this.fileName = fileName;
    }

    public BigInteger[] getFileLen() {
        return fileLen;
    }

    public void setFileLen(BigInteger[] fileLen) {
        this.fileLen = fileLen;
    }

    public String[] getFileType() {
        return fileType;
    }

    public void setFileType(String[] fileType) {
        this.fileType = fileType;
    }

    public String getLogDate() {
        return logDate;
    }

    public void setLogDate(String logDate) {
        this.logDate = logDate;
    }
    
    
}
