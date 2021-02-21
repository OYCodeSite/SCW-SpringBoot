package com.oy.scw.webui.service.exp.handler;

import com.oy.scw.project.vo.resp.AppResponse;

import com.oy.scw.webui.service.TMemberServiceFeign;
import com.oy.scw.webui.vo.resp.UserAddressVo;
import com.oy.scw.webui.vo.resp.UserRespVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @Author OY
 * @Date 2021/2/16
 */
@Slf4j
@Component
public class TMemberServiceFeignExcetionHandle implements TMemberServiceFeign {

    @Override
    public AppResponse<UserRespVo> login(String loginacct, String password) {
        AppResponse<UserRespVo> resp = AppResponse.fail(null);
        resp.setMsg("调用远程服务【登录】失败");

        log.debug("调用远程服务【登录】失败");

        return resp;
    }

    @Override
    public AppResponse<List<UserAddressVo>> address(String accessToken) {
        AppResponse<List<UserAddressVo>> resp = AppResponse.fail(null);
        resp.setMsg("调用远程服务【根据用户查询地址】失败");
        log.error("调用远程服务【根据用户查询地址】失败");
        return resp;
    }
}
