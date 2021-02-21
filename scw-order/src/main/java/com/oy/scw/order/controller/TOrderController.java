package com.oy.scw.order.controller;

import com.oy.scw.order.bean.TOrder;
import com.oy.scw.order.server.TOrderService;
import com.oy.scw.order.vo.resp.OrderInfoSubmitVo;
import com.oy.scw.project.vo.resp.AppResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Author OY
 * @Date 2021/2/20
 */
@Slf4j
@Controller
@Api("订单个人信息模块")
public class TOrderController {

    @Autowired
    TOrderService orderService;

    @ResponseBody
    @ApiOperation(value = "保存订单信息")
    @RequestMapping("/order/saveOrder")
    public AppResponse<TOrder> saveOrder(@RequestBody OrderInfoSubmitVo vo){

        try {
            log.debug("保存订单开始={}",vo);

            TOrder order = orderService.saveOrder(vo);
            AppResponse<TOrder> resp = AppResponse.ok(order);
            return resp;
        } catch (Exception e) {
            e.printStackTrace();
            AppResponse<TOrder> resp = AppResponse.fail(null);
            resp.setMsg("保存订单失败");
            log.debug("保存订单失败");
            return resp;
        }
    }
}

