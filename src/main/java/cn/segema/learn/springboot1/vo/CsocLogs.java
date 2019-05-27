package cn.segema.learn.springboot1.vo;

public class CsocLogs {

    private String metadata_guid;

    private String event_typeA;

    private String event_typeB;

    private String event_description;
    
    private String source_endpoint_ip;
    private String source_geo_countryCode;
    private String source_geo_city;
    
    private String destination_endpoint_ip;
    private String destination_geo_countryCode;
    private String destination_geo_city;
    public String getMetadata_guid() {
        return metadata_guid;
    }
    public void setMetadata_guid(String metadata_guid) {
        this.metadata_guid = metadata_guid;
    }
    public String getEvent_typeA() {
        return event_typeA;
    }
    public void setEvent_typeA(String event_typeA) {
        this.event_typeA = event_typeA;
    }
    public String getEvent_typeB() {
        return event_typeB;
    }
    public void setEvent_typeB(String event_typeB) {
        this.event_typeB = event_typeB;
    }
    public String getEvent_description() {
        return event_description;
    }
    public void setEvent_description(String event_description) {
        this.event_description = event_description;
    }
    public String getSource_endpoint_ip() {
        return source_endpoint_ip;
    }
    public void setSource_endpoint_ip(String source_endpoint_ip) {
        this.source_endpoint_ip = source_endpoint_ip;
    }
    public String getSource_geo_countryCode() {
        return source_geo_countryCode;
    }
    public void setSource_geo_countryCode(String source_geo_countryCode) {
        this.source_geo_countryCode = source_geo_countryCode;
    }
    public String getSource_geo_city() {
        return source_geo_city;
    }
    public void setSource_geo_city(String source_geo_city) {
        this.source_geo_city = source_geo_city;
    }
    public String getDestination_endpoint_ip() {
        return destination_endpoint_ip;
    }
    public void setDestination_endpoint_ip(String destination_endpoint_ip) {
        this.destination_endpoint_ip = destination_endpoint_ip;
    }
    public String getDestination_geo_countryCode() {
        return destination_geo_countryCode;
    }
    public void setDestination_geo_countryCode(String destination_geo_countryCode) {
        this.destination_geo_countryCode = destination_geo_countryCode;
    }
    public String getDestination_geo_city() {
        return destination_geo_city;
    }
    public void setDestination_geo_city(String destination_geo_city) {
        this.destination_geo_city = destination_geo_city;
    }

}
