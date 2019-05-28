package cn.segema.learn.springboot1.controller;

import java.math.BigInteger;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cn.segema.learn.springboot1.domain.HttpLog;
import cn.segema.learn.springboot1.domain.User;
import cn.segema.learn.springboot1.repository.HttpLogRepository;
import cn.segema.learn.springboot1.vo.HttpLogVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

@Api(value = "HTTP日志")
@RestController
@RequestMapping("/http")
public class HttpLogController {

    @Autowired
    protected HttpLogRepository httpLogRepository;

    @ApiOperation(value = "根据条件查询 appProto")
    @GetMapping("/app-proto")
    public ResponseEntity total(String appProto) {
        HttpLog httpLog = new HttpLog();
        httpLog.setAppProto(appProto);
        Example<HttpLog> example = Example.of(httpLog);
        List<HttpLog> httpLogList = httpLogRepository.findAll(example);
        return new ResponseEntity(httpLogList, HttpStatus.OK);
    }

    @ApiOperation(value = "根据分区时间查询")
    @GetMapping("/list-date")
    public ResponseEntity listByLogDate(String logDate) {
        HttpLog httpLog = new HttpLog();
        httpLog.setLogDate(logDate);
        Example<HttpLog> example = Example.of(httpLog);
        Sort sort = new Sort(Direction.DESC, "timestamp");
        Pageable pageable = new PageRequest(0, 30, sort);
        Page<HttpLog> page = httpLogRepository.findAll(example, pageable);
        List<HttpLog> alarmLists = page.getContent();
        return new ResponseEntity(alarmLists, HttpStatus.OK);
    }

    @ApiOperation(value = "根据id获取http日志", notes = "根据id获取http日志")
    @ApiImplicitParams({@ApiImplicitParam(name = "id", value = "http日志id", required = true, paramType = "path")})
    @GetMapping("/find/{id}")
    public ResponseEntity findHttpLog(@PathVariable BigInteger id) {
        HttpLog httpLog = httpLogRepository.findById(id);
        return new ResponseEntity(httpLog, HttpStatus.OK);
    }
    
    
    @ApiOperation(value = "获取全部http日志", notes = "获取全部http日志")
    @GetMapping("/all")
    public ResponseEntity getAllHttpLogs() {
        System.out.println("只有第一次才会打印sql语句");
         List<HttpLog> httpLogs = httpLogRepository.getAllHttpLogs();
         return new ResponseEntity(httpLogs, HttpStatus.OK);
    }

    @ApiOperation(value = "新增http日志", notes = "新增http日志")
    @PostMapping("/create")
    public ResponseEntity createHttpLog(@RequestBody HttpLogVO httpLog) {
        httpLogRepository.createHttpLog(httpLog);

        // httpLogRepository.addHttpLog(httpLog.getHttpLogId(),httpLog.getHttpLogName(),httpLog.getPassword());
        return new ResponseEntity(httpLog, HttpStatus.OK);
    }

    @GetMapping("/delete/{id}")
    public ResponseEntity deleteHttpLog(@PathVariable BigInteger id) {
        httpLogRepository.deleteById(id);
        return new ResponseEntity("success", HttpStatus.OK);
    }

    @PutMapping("/update")
    public ResponseEntity updateHttpLog(@RequestBody HttpLogVO httpLog) {
        httpLogRepository.updateHttpLog(httpLog);
        return new ResponseEntity(httpLog, HttpStatus.OK);
    }

}
