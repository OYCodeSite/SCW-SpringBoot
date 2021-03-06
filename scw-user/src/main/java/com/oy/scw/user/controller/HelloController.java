package com.oy.scw.user.controller;

import io.swagger.annotations.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author OY
 * @Date 2021/2/11
 */

import org.springframework.web.bind.annotation.PostMapping;

@Api(tags = {"第一个Swagger测试"}) //描述当前类是做什么的
@RestController
public class HelloController {

    @ApiOperation("测试方法Hello")
    @ApiImplicitParams(value = {
            @ApiImplicitParam(name = "name", value = "姓名", required = true),
            @ApiImplicitParam(name = "age", value = "年龄")
    })
    @GetMapping("/hello")
    public String hello(String name, int age) {
        return "OK:" + name;
    }

    @ApiOperation("测试方法Hello2")
    @ApiImplicitParams(value = {
            @ApiImplicitParam(name = "name", value = "姓名", required = true),
            @ApiImplicitParam(name = "email", value = "电子邮件")
    })
    @PostMapping("/user")
    public User user(String name, String email) {
        User user = new User();
        user.setName(name);
        user.setEmail(email);
        return user;
    }

}



