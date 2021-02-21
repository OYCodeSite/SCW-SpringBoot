package com.oy.scw.project.service.Feign;

import com.oy.scw.project.service.Feign.handle.MemberServiceFeignExceptionHandle;
import com.oy.scw.project.vo.resp.AppResponse;
import com.oy.scw.project.vo.resp.TMember;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @Author OY
 * @Date 2021/2/18
 */

@FeignClient(value = "SCW-USER",fallback = MemberServiceFeignExceptionHandle.class)
public interface MemberServiceFeign {
    @GetMapping("/user/info/getMebmerById")
    public AppResponse<TMember> getMebmerById(@RequestParam("id") Integer id);
}
