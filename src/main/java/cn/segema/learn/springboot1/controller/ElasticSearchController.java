package cn.segema.learn.springboot1.controller;

import java.io.IOException;
import java.util.Date;

import org.elasticsearch.action.ActionListener;
import org.elasticsearch.action.admin.indices.alias.Alias;
import org.elasticsearch.action.admin.indices.create.CreateIndexRequest;
import org.elasticsearch.action.admin.indices.create.CreateIndexResponse;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.unit.TimeValue;
import org.elasticsearch.common.xcontent.XContentType;
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

import cn.segema.learn.springboot1.util.DateUtil;
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
    
    @Autowired
    private RestHighLevelClient restHighLevelClient;

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
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        searchSourceBuilder.from(0);
        searchSourceBuilder.size(20);
       // searchSourceBuilder.query(QueryBuilders.matchQuery("alarm_level",searchContent));
        searchSourceBuilder.query(QueryBuilders.matchAllQuery());
        Search search = new Search.Builder(searchSourceBuilder.toString()).addIndex("index_nta_alarm_log_*").addType("type_nta_alarm_log").build();
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
    
    
    @GetMapping("/test3/{searchContent}")
    public ResponseEntity test3(@PathVariable String  searchContent) throws IOException {
        
        CreateIndexRequest request = new CreateIndexRequest("twitter_two");//创建索引
        //创建的每个索引都可以有与之关联的特定设置。
        request.settings(Settings.builder()
                .put("index.number_of_shards", 3)
                .put("index.number_of_replicas", 1)
        );
        
        //创建索引时创建文档类型映射
        request.mapping("tweet",//类型定义
                "  {\n" +
                        "    \"tweet\": {\n" +
                        "      \"properties\": {\n" +
                        "        \"message\": {\n" +
                        "          \"type\": \"text\"\n" +
                        "        }\n" +
                        "      }\n" +
                        "    }\n" +
                        "  }",//类型映射，需要的是一个JSON字符串
                XContentType.JSON);
      //为索引设置一个别名
        request.alias(
                new Alias("twitter_alias")
        );
        
      //可选参数
        request.timeout(TimeValue.timeValueMinutes(2));//超时,等待所有节点被确认(使用TimeValue方式)
        //同步执行
        CreateIndexResponse createIndexResponse = restHighLevelClient.indices().create(request);
        
        //异步方法不会阻塞并立即返回。
        ActionListener<CreateIndexResponse> listener = new ActionListener<CreateIndexResponse>() {
            @Override
            public void onResponse(CreateIndexResponse createIndexResponse) {
                //如果执行成功，则调用onResponse方法;
            }
            @Override
            public void onFailure(Exception e) {
                //如果失败，则调用onFailure方法。
            }
        };
        restHighLevelClient.indices().createAsync(request, listener);//要执行的CreateIndexRequest和执行完成时要使用的ActionListener
        
        return new ResponseEntity(createIndexResponse, HttpStatus.OK);
    }
    

    @GetMapping("/test4/{searchContent}")
    public ResponseEntity test4(@PathVariable String  searchContent) throws IOException {
        
        CreateIndexRequest request = new CreateIndexRequest("twitter_four");//创建索引
        //创建的每个索引都可以有与之关联的特定设置。
        request.settings(Settings.builder()
                .put("index.number_of_shards", 3)
                .put("index.number_of_replicas", 1)
        );
        
        //创建索引时创建文档类型映射
        request.mapping("tweet",//类型定义
                "  {\n" +
                        "    \"tweet\": {\n" +
                        "      \"properties\": {\n" +
                        "        \"message\": {\n" +
                        "          \"type\": \"text\"\n" +
                        "        }\n" +
                        "      }\n" +
                        "    }\n" +
                        "  }",//类型映射，需要的是一个JSON字符串
                XContentType.JSON);
        //为索引设置一个别名
        request.alias(
                new Alias("twitter_alias")
        );
        
        request.timeout(TimeValue.timeValueMinutes(2));//超时,等待所有节点被确认(使用TimeValue方式)
        
        //异步方法不会阻塞并立即返回。
        ActionListener<CreateIndexResponse> listener = new ActionListener<CreateIndexResponse>() {
            @Override
            public void onResponse(CreateIndexResponse createIndexResponse) {
                //如果执行成功，则调用onResponse方法;
            }
            @Override
            public void onFailure(Exception e) {
                //如果失败，则调用onFailure方法。
            }
        };
        restHighLevelClient.indices().createAsync(request, listener);//要执行的CreateIndexRequest和执行完成时要使用的ActionListener
        
        return new ResponseEntity("success", HttpStatus.OK);
    }
    
    
}
