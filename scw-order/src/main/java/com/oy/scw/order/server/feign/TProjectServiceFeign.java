package com.oy.scw.order.server.feign;

import com.oy.scw.order.server.feign.handle.TProjectServiceFeignExceptionHandle;
import com.oy.scw.order.vo.resp.TReturn;
import com.oy.scw.project.vo.resp.AppResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @Author OY
 * @Date 2021/2/20
 */
@FeignClient(value="SCW-PROJECT",fallback= TProjectServiceFeignExceptionHandle.class)
public interface TProjectServiceFeign {

    @GetMapping("/project/details/returns/info/{returnId}")
    public AppResponse<TReturn> returnInfo(@PathVariable("returnId") Integer returnId);
}
