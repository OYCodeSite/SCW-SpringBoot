package com.oy.scw.webui.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Author OY
 * @Date 2021/2/20
 */
@Slf4j
@Controller
public class TMemberController {

    @RequestMapping("/member/mineatcrowdfunding")
    public String myOrderList() {

        log.debug("支付后，同步请求处理.....");

        return "member/minecrowdfunding";
    }
}
