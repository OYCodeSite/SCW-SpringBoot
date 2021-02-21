package com.oy.scw.webui.service;





import com.oy.scw.project.vo.resp.AppResponse;
import com.oy.scw.webui.vo.resp.UserAddressVo;
import com.oy.scw.webui.vo.resp.UserRespVo;
import com.oy.scw.webui.service.exp.handler.TMemberServiceFeignExcetionHandle;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * @Author OY
 * @Date 2021/2/16
 */
@FeignClient(value="SCW-USER", fallback = TMemberServiceFeignExcetionHandle.class)
public interface TMemberServiceFeign {

    @PostMapping("/user/login")
    public AppResponse<UserRespVo> login(@RequestParam(value = "loginacct") String loginacct, @RequestParam(value = "password") String password);

    @GetMapping("/user/info/address")
    public AppResponse<List<UserAddressVo>> address(@RequestParam("accessToken") String accessToken);

}
