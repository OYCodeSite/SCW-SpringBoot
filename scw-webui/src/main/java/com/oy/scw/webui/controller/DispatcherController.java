package com.oy.scw.webui.controller;


import com.oy.scw.project.vo.resp.AppResponse;
import com.oy.scw.webui.service.TMemberServiceFeign;
import com.oy.scw.webui.service.TProjectServiceFeign;
import com.oy.scw.webui.vo.resp.ProjectVo;
import com.oy.scw.webui.vo.resp.UserRespVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @Author OY
 * @Date 2021/2/16
 */

@Controller
public class DispatcherController {

    @Autowired
    TMemberServiceFeign memberServiceFeign;

    @Autowired
    TProjectServiceFeign projectServiceFeign;

    @Autowired
    RedisTemplate redisTemplate;

    @RequestMapping("/logout")
    public String logout(HttpSession session){
        if(session != null){
            session.removeAttribute("loginMember");
            session.invalidate();
        }
        return "redirect:/index";
    }


    @RequestMapping("/doLogin")
    public String doLogin(String loginacct,@RequestParam("userpswd") String password, HttpSession session){

        AppResponse<UserRespVo> resp = memberServiceFeign.login(loginacct, password);

        UserRespVo data = resp.getData();

        if(data == null){
            return "login";
        }

        session.setAttribute("loginMember",data);

        String preUrl = (String) session.getAttribute("preUrl");

        if(StringUtils.isEmpty(preUrl)){

            return "redirect:/index";
        }else{
            return "redirect:"+preUrl;
        }

    }

    @RequestMapping("/login")
    public String login(){
        return "login";
    }

    @GetMapping("/index")
    public String index(Model model){

        List<ProjectVo> data = (List<ProjectVo>) redisTemplate.opsForValue().get("projectInfo");

        if(data==null){
            AppResponse<List<ProjectVo>> resp = projectServiceFeign.all();
            data = resp.getData();
            redisTemplate.opsForValue().set("projectInfo",data,1, TimeUnit.HOURS);
        }

        model.addAttribute("projectVoList", data);


        return "index";
    }

    @GetMapping("/")
    public String indexMain(Model model){

        List<ProjectVo> data = (List<ProjectVo>) redisTemplate.opsForValue().get("projectInfo");

        if(data==null){
            AppResponse<List<ProjectVo>> resp = projectServiceFeign.all();
            data = resp.getData();
            redisTemplate.opsForValue().set("projectInfo",data,1, TimeUnit.HOURS);
        }

        model.addAttribute("projectVoList", data);


        return "index";
    }
}
