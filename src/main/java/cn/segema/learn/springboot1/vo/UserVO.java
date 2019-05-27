package cn.segema.learn.springboot1.vo;

import java.math.BigInteger;
import io.swagger.annotations.ApiModelProperty;

public class UserVO {
    
    @ApiModelProperty(value = "用户id")
    private BigInteger userId;
    
    @ApiModelProperty(value = "用户名")
    private String userName;
    
    @ApiModelProperty(value = "密码")
    private String password;

}
