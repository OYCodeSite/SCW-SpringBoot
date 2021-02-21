package com.oy.scw.webui.service;

import com.oy.scw.project.vo.resp.AppResponse;
import com.oy.scw.webui.service.exp.handler.TOrderServiceFeignExceptionHandle;
import com.oy.scw.webui.vo.rep.OrderInfoSubmitVo;
import com.oy.scw.webui.vo.resp.TOrder;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient(value = "SCW-ORDER", fallback = TOrderServiceFeignExceptionHandle.class)
public interface TOrderServiceFeign {

    /**
     * 远程调用参数传递问题
     * 	①简单   @RequestParam    @PathVariable
     *  ②复杂对象   @RequestBody
     * @param vo
     * @return
     */
    @RequestMapping("/order/saveOrder")
    public AppResponse<TOrder> saveOrder(@RequestBody OrderInfoSubmitVo vo);
}
