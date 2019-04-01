package com.swad.test.controller;

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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.swad.test.entity.HttpLog;
import com.swad.test.repository.HttpLogRepository;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/test/http")
@Api(value = "HTTP日志")
public class HttpLogController {
	
	@Autowired
	protected HttpLogRepository httpLogRepository;
	
	@ApiOperation(value = "根据条件查询 appProto")
	@GetMapping("/app-proto")
	public ResponseEntity total(String appProto) {
	    HttpLog httpLog = new HttpLog();
	    httpLog.setAppProto(appProto);
		Example<HttpLog> example = Example.of(httpLog); 
		List<HttpLog> httpLogList= httpLogRepository.findAll(example);
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
		 Page<HttpLog> page = httpLogRepository.findAll(example,pageable);
		 List<HttpLog> alarmLists = page.getContent();
		return new ResponseEntity(alarmLists, HttpStatus.OK);
	}
}
