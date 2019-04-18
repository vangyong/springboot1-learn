package com.swad.test.controller;

import java.io.IOException;
import java.util.Date;

import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.aggregations.AggregationBuilder;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.swad.test.util.DateUtil;
import io.searchbox.client.JestClient;
import io.searchbox.core.Search;
import io.searchbox.core.SearchResult;
import io.swagger.annotations.Api;

@RestController
@RequestMapping("/es")
@Api(value = "ElasticSearch查询")
public class ElasticSearchController {

    @Autowired
    private JestClient jestClient;

    @GetMapping("/test1/{statisticType}")
    public ResponseEntity test1(@PathVariable String  statisticType) {
        Date startDate  =DateUtil.getStartTime(statisticType);
        Date endDate  =new Date();
        
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();
        boolQueryBuilder.filter().add(QueryBuilders.rangeQuery("timestamp_reportedTime")
                .gte(startDate).lte(endDate));
        
        AggregationBuilder aggregationBuilder = AggregationBuilders.terms("group_by_attacker")
            .field("source_endpoint_ip")
            .size(100)
            .subAggregation(AggregationBuilders.cardinality("count_attack").field("destination_endpoint_ip"));
        
        searchSourceBuilder.aggregation(aggregationBuilder);
        searchSourceBuilder.size(0);
        searchSourceBuilder.query(boolQueryBuilder);
        
        Search search = new Search.Builder(searchSourceBuilder.toString()).addIndex("csoc-logs-*").addType("logs").build();
        SearchResult searchResult = null;
         try {
             searchResult = jestClient.execute(search);
             if(searchResult!=null) {
                 return new ResponseEntity(searchResult.getJsonString(), HttpStatus.OK);
             }
        } catch (IOException e) {
             e.printStackTrace();
        }
        return new ResponseEntity(searchResult, HttpStatus.OK);
    }

    @GetMapping("/test2/{searchContent}")
    public ResponseEntity test2(@PathVariable String  searchContent) {
        //String searchContent ="WEB漏洞扫描";
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        //searchSourceBuilder.query(QueryBuilders.queryStringQuery(searchContent));
        //searchSourceBuilder.field("event_description");
        searchSourceBuilder.query(QueryBuilders.matchQuery("event_description",searchContent));
        Search search = new Search.Builder(searchSourceBuilder.toString()).addIndex("csoc-alert-*").addType("logs").build();
        SearchResult searchResult = null;
         try {
             searchResult = jestClient.execute(search);
             if(searchResult!=null) {
                 return new ResponseEntity(searchResult.getJsonString(), HttpStatus.OK);
             }
        } catch (IOException e) {
             e.printStackTrace();
        }
        return new ResponseEntity(searchResult, HttpStatus.OK);
    }
}
