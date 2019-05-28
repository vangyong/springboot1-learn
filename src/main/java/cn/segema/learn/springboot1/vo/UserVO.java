package cn.segema.learn.springboot1.vo;

import java.io.Serializable;
import java.math.BigInteger;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel("用户VO")
@Data
public class UserVO implements Serializable{
    
    @ApiModelProperty(value = "用户id")
    private BigInteger userId;
    
    @ApiModelProperty(value = "用户名")
    private String userName;
    
    @ApiModelProperty(value = "密码")
    private String password;

}
