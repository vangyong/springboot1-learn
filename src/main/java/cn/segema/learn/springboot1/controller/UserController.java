package cn.segema.learn.springboot1.controller;

import java.math.BigInteger;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import cn.segema.learn.springboot1.domain.User;
import cn.segema.learn.springboot1.repository.UserRepository;
import cn.segema.learn.springboot1.vo.UserVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

@Api(value = "用户管理")
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @ApiOperation(value = "获取全部用户", notes = "获取全部用户")
    @GetMapping("/all")
    public List<User> getAllUsers() {
        System.out.println("只有第一次才会打印sql语句");
        return userRepository.getAllUsers();
    }

    @ApiOperation(value = "根据id获取用户", notes = "根据id获取用户")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "id", value = "用户id", required = true, paramType = "path")})
    @GetMapping("/find/{id}")
    public ResponseEntity findUser(@PathVariable BigInteger id) {
        User user = userRepository.findById(id);
        return new ResponseEntity(user, HttpStatus.OK);
    }

    @ApiOperation(value = "新增用户", notes = "新增用户")
    @PostMapping("/create")
    public ResponseEntity createUser(@RequestBody UserVO user) {
        userRepository.createUser(user);
        
//        userRepository.addUser(user.getUserId(),user.getUserName(),user.getPassword());
        return new ResponseEntity(user, HttpStatus.OK);
    }

    @GetMapping("/delete/{id}")
    public ResponseEntity deleteUser(@PathVariable BigInteger id) {
        userRepository.deleteById(id);
        return new ResponseEntity("success", HttpStatus.OK);
    }

    @PutMapping("/update")
    public ResponseEntity updateUser(@RequestBody UserVO user) {
        userRepository.updateUser(user);
        return new ResponseEntity(user, HttpStatus.OK);
    }

    @ApiOperation(value = "分页获取用户", notes = "分页获取用户")
    @ApiImplicitParams({@ApiImplicitParam(name = "page", value = "当前页", required = true, paramType = "query"),
        @ApiImplicitParam(name = "limit", value = "每页数", required = true, paramType = "query"),
        @ApiImplicitParam(name = "sort", value = "排序列", required = false, paramType = "query")})
    @GetMapping("/page")
    public ResponseEntity listByPage(@RequestParam(defaultValue = "1") int page,
        @RequestParam(defaultValue = "20") int limit, @RequestParam(defaultValue = "user_id") String sort,
        UserVO userVO) {
        Sort sortOrder = new Sort(Sort.Direction.DESC, sort);
        Pageable pageable = new PageRequest(page - 1, limit, sortOrder);
        Page<User> userPage = userRepository.findByPage(userVO, pageable);
        return new ResponseEntity(userPage, HttpStatus.OK);
    }
}
