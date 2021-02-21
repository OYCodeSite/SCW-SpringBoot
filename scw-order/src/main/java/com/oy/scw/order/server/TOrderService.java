package com.oy.scw.order.server;

import com.oy.scw.order.bean.TOrder;
import com.oy.scw.order.vo.resp.OrderInfoSubmitVo;

public interface TOrderService {
    TOrder saveOrder(OrderInfoSubmitVo vo);
}
