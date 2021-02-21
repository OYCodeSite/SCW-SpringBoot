package com.oy.scw.order.server.impl;

import com.oy.scw.enums.OrderStatusEnumes;
import com.oy.scw.order.bean.TOrder;
import com.oy.scw.order.mapper.TOrderMapper;
import com.oy.scw.order.server.TOrderService;
import com.oy.scw.order.server.feign.TProjectServiceFeign;
import com.oy.scw.order.vo.resp.OrderInfoSubmitVo;
import com.oy.scw.order.vo.resp.TReturn;
import com.oy.scw.project.vo.resp.AppResponse;
import com.oy.scw.util.AppDateUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.UUID;

/**
 * @Author OY
 * @Date 2021/2/20
 */
@Slf4j
@Service
public class TOrderServiceImpl implements TOrderService {

    @Autowired
    StringRedisTemplate redisTemplate;

    @Autowired
    TProjectServiceFeign projectServiceFeign;

    @Autowired
    TOrderMapper tOrderMapper;

    @Override
    public TOrder saveOrder(OrderInfoSubmitVo vo) {
        TOrder order = new TOrder();

        String accessToken = vo.getAccessToken();
        String memberId = redisTemplate.opsForValue().get(accessToken);

        order.setMemberid(Integer.parseInt(memberId));

        order.setProjectid(vo.getProjectid());
        order.setReturnid(vo.getReturnid());

        String ordernum = UUID.randomUUID().toString().replace("-","");
        order.setOrdernum(ordernum);

        order.setCreatedate(AppDateUtils.getFormatTime());

        log.debug("vo.getReturnid()的值为={}",vo.getReturnid());
        // 调用远程服务;
        AppResponse<TReturn> respTReturn = projectServiceFeign.returnInfo(vo.getReturnid());
        TReturn retObj = respTReturn.getData();

        log.debug("retObj={}",retObj);
        Integer totalMoney = vo.getRtncount() * retObj.getSupportmoney() + retObj.getFreight() ;

        order.setMoney(totalMoney);
        order.setRtncount(vo.getRtncount());
        order.setStatus(OrderStatusEnumes.UNPAY.getCode()+"");
        order.setAddress(vo.getAddress());
        order.setInvoice(vo.getInvoice().toString());
        order.setInvoictitle(vo.getInvoictitle());
        order.setRemark(vo.getRemark());

        tOrderMapper.insertSelective(order);

        log.debug("业务层保存订单={}",order);


        return order;
    }
}
