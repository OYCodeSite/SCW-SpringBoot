package com.oy.session.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

/**
 * @Author OY
 * @Date 2021/2/16
 */
@RestController
public class SpringSessionController {

    @GetMapping("/set")
    public String setSession(HttpSession session){
        session.setAttribute("msg", "Hello");
        return "ok";
    }

    @GetMapping("/get")
    public String getSession(HttpSession session){
        return (String) session.getAttribute("msg");
    }

}
