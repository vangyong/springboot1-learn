 package cn.segema.learn.springboot1.nats;

import java.math.BigInteger;

import cn.segema.learn.springboot1.domain.User;

public class Test {

    public static void main(String[] args) {
        User user = new User();
        user.setUserId(BigInteger.valueOf(100L));
        user.setUserName("userName");
        System.out.println(user.toString());
    }

}
