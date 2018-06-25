package com.nmm.study.controller;

import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 连接测试类
 */
@Controller
public class TestController {

    @ResponseBody
    @RequestMapping("/login/{name}/{password}/{role}")
    public String login(@PathVariable String name,@PathVariable String password,@PathVariable String role) {
        SecurityContextHolder.getContext().setAuthentication(new UsernamePasswordAuthenticationToken(name,password,
                AuthorityUtils.commaSeparatedStringToAuthorityList(role)));
        return "hello " + name + ",login success";
    }
    @ResponseBody
    @RequestMapping("/hello/{name}")
    public String hello(@PathVariable  String name){
        sayHello();
        return "hello ,autho user:" + name;
    }
    @ResponseBody
    @RequestMapping("/sayHello")
    public String sayHello() {
       Object detail = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
       return "sayHello " + detail;
    }
    @PreAuthorize("true")
    public void check(){
        System.out.println("hello check");
    }

}
