 package cn.segema.learn.springboot1.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @description 模拟测试接口
 * @author wangyong
 * @createDate 2020/08/17
 */
@RestController
public class ComprehensiveController {
    
    private static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    
    
    @GetMapping("/v1/comprehensive/level")
    public ResponseEntity getSafeLevel() {
        Map<String, Object> level = new HashMap<String,Object>();
        level.put("level", "安全");
        return new ResponseEntity(level, HttpStatus.OK);
    }
    
    
    
    /**
     * @description 数据统计
     * @return
     */
    @GetMapping("/v1/comprehensive/statistics")
    public ResponseEntity getStatistics() {
        Map<String, Object> statistics = new HashMap<String,Object>();
        statistics.put("assetTotal", 520);
        statistics.put("riskAssetTotal", 15);
        statistics.put("threatenTotal", 30);
        statistics.put("loopholeTotal", 24);
        statistics.put("childCompanyTotal", 120);
        statistics.put("unitTotal", 3600);
        
        return new ResponseEntity(statistics, HttpStatus.OK);
    }

    /**
     * @description 资产等级分布
     * @return
     */
    @GetMapping("/v1/comprehensive/assetLevel")
    public ResponseEntity getAssetLevel() {
        List<Map<String, Object>> assetLevelList = new ArrayList<Map<String,Object>>();
        Map<String, Object> asset1 = new HashMap<String, Object>();
        asset1.put("level", 1001);
        asset1.put("name", "级别1");
        asset1.put("value", 15);
        assetLevelList.add(asset1);
        
        Map<String, Object> asset2 = new HashMap<String, Object>();
        asset2.put("level", 1002);
        asset2.put("name", "级别2");
        asset2.put("value", 23);
        assetLevelList.add(asset2);
        
        Map<String, Object> asset3 = new HashMap<String, Object>();
        asset3.put("level", 1);
        asset3.put("name", "level1");
        asset3.put("value", 100);
        assetLevelList.add(asset3);
        
        return new ResponseEntity(assetLevelList, HttpStatus.OK);
    }

    /**
     * @description 威胁等级分布
     * @return
     */
    @GetMapping("/v1/comprehensive/threatenLevel")
    public ResponseEntity getThreatenLevel() {
        List<Map<String, Object>> threatenLevelList = new ArrayList<Map<String,Object>>();
        Map<String, Object> threatenLeve1 = new HashMap<String, Object>();
        threatenLeve1.put("level", 1);
        threatenLeve1.put("name", "严重");
        threatenLeve1.put("value", 15);
        threatenLevelList.add(threatenLeve1);
        
        Map<String, Object> threatenLeve2 = new HashMap<String, Object>();
        threatenLeve2.put("level", 3);
        threatenLeve2.put("name", "高危");
        threatenLeve2.put("value", 23);
        threatenLevelList.add(threatenLeve2);
        
        
        return new ResponseEntity(threatenLevelList, HttpStatus.OK);
    }

    /**
     * @description 漏洞等级分布
     * @return
     */
    @GetMapping("/v1/comprehensive/loopholeLevel")
    public ResponseEntity getLoopholeLevel() {
        List<Map<String, Object>> loopholeLevelList = new ArrayList<Map<String,Object>>();
        Map<String, Object> loopholeLevel1 = new HashMap<String, Object>();
        loopholeLevel1.put("level", 1);
        loopholeLevel1.put("name", "超危");
        loopholeLevel1.put("value", 15);
        loopholeLevelList.add(loopholeLevel1);
        
        Map<String, Object> loopholeLevel2 = new HashMap<String, Object>();
        loopholeLevel2.put("level", 5);
        loopholeLevel2.put("name", "高危");
        loopholeLevel2.put("value", 23);
        loopholeLevelList.add(loopholeLevel2);
        
        return new ResponseEntity(loopholeLevelList, HttpStatus.OK);
    }

    /**
     * @description 攻击次数排行Top5
     * @return
     */
    @GetMapping("/v1/comprehensive/attackedTop5")
    public ResponseEntity getAttackedTop5() {
        List<Map<String, Object>> attackedTop5 = new ArrayList<Map<String,Object>>();
        Map<String, Object> attacked1 = new HashMap<String, Object>();
        attacked1.put("name", "北京第一油库");
        attacked1.put("value", 15);
        attackedTop5.add(attacked1);
        
        Map<String, Object> attacked2 = new HashMap<String, Object>();
        attacked2.put("name", "成都双流国际机场油库");
        attacked2.put("value", 23);
        attackedTop5.add(attacked2);
        
        return new ResponseEntity(attackedTop5, HttpStatus.OK);
    }

    /**
     * @description 攻击阶段日志趋势
     * @return
     */
    @GetMapping("/v1/comprehensive/trend")
    public ResponseEntity getTrend() {
        Map<String, Object> trend = new HashMap<String,Object>();
        trend.put("time", new String[]{"0:00","2:00","4:00","6:00","8:00","10:00","12:00","14:00","16:00","18:00","20:00","22:00"});
        
        List<Map<String, Object>> dataList = new ArrayList<Map<String,Object>>();
        Map<String, Object> data1 = new HashMap<String, Object>();
        data1.put("key", 1);
        data1.put("name", "侦查扫描");
        data1.put("value", 15);
        dataList.add(data1);
        
        Map<String, Object> data2 = new HashMap<String, Object>();
        data2.put("key", 2);
        data2.put("name", "SQL注入");
        data2.put("value", 23);
        dataList.add(data2);
        trend.put("data",dataList);
        
        return new ResponseEntity(trend, HttpStatus.OK);
    }

    /**
     * @description 工控设备异常数据
     * @return
     */
    @GetMapping("/v1/comprehensive/abnormalStatistics")
    public ResponseEntity getAbnormalStatistics() {
        List<Map<String, Object>> abnormalStatisticsList = new ArrayList<Map<String,Object>>();
        
        Map<String, Object> data1 = new HashMap<String, Object>();
        data1.put("total", 100);
        data1.put("abnormalCount", 15);
        abnormalStatisticsList.add(data1);
        
        Map<String, Object> data2 = new HashMap<String, Object>();
        data2.put("total", 100);
        data2.put("abnormalCount", 10);
        abnormalStatisticsList.add(data2);
        
        return new ResponseEntity(abnormalStatisticsList, HttpStatus.OK);
    }

    /**
     * @description 受攻击资产排行
     * @return
     */
    @GetMapping("/v1/comprehensive/attackedAsset")
    public ResponseEntity getAttackedAsset() {
        List<Map<String, Object>> attackedAssetList = new ArrayList<Map<String,Object>>();
        Map<String, Object> data1 = new HashMap<String, Object>();
        data1.put("ip", "192.168.10.10");
        data1.put("value", 15);
        attackedAssetList.add(data1);
        
        Map<String, Object> data2 = new HashMap<String, Object>();
        data2.put("ip", "192.168.10.11");
        data2.put("value", 10);
        attackedAssetList.add(data2);
        
        return new ResponseEntity(attackedAssetList, HttpStatus.OK);
    }

    /**
     * @description 最新安全事件
     * @return
     */
    @GetMapping("/v1/comprehensive/event")
    public ResponseEntity getEvent() {
        List<Map<String, Object>> eventList = new ArrayList<Map<String,Object>>();
        Map<String, Object> data1 = new HashMap<String, Object>();
        data1.put("level", 4);
        data1.put("time", sdf.format(new Date()));
        data1.put("describe", "75.12.103.12受到特洛伊木马通信攻击");
        data1.put("from", "OT");
        eventList.add(data1);
        
        Map<String, Object> data2 = new HashMap<String, Object>();
        data2.put("level", 2);
        data2.put("time", sdf.format(new Date()));
        data1.put("describe", "192.168.0.1受到SQL注入攻击");
        data1.put("from", "IT");
        eventList.add(data2);
        
        return new ResponseEntity(eventList, HttpStatus.OK);
    }

    
    
    
    
}
