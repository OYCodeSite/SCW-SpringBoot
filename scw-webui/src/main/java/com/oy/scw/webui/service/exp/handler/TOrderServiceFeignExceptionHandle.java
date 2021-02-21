package com.oy.scw.webui.service.exp.handler;

import com.oy.scw.project.vo.resp.AppResponse;
import com.oy.scw.webui.service.TOrderServiceFeign;
import com.oy.scw.webui.vo.rep.OrderInfoSubmitVo;
import com.oy.scw.webui.vo.resp.TOrder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @Author OY
 * @Date 2021/2/20
 */
@Slf4j
@Component
public class TOrderServiceFeignExceptionHandle implements TOrderServiceFeign {
    @Override
    public AppResponse<TOrder> saveOrder(OrderInfoSubmitVo vo) {
        AppResponse<TOrder> resp = AppResponse.fail(null);
        resp.setMsg("调用远程服务【保存订单失败】失败");
        log.error("调用远程服务【保存订单失败】失败");
        return resp;
    }
}
