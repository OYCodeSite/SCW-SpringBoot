package com.oy.scw.order.server.feign.handle;

import com.oy.scw.order.server.feign.TProjectServiceFeign;
import com.oy.scw.order.vo.resp.TReturn;
import com.oy.scw.project.vo.resp.AppResponse;
import org.springframework.stereotype.Component;

/**
 * @Author OY
 * @Date 2021/2/20
 */
@Component
public class TProjectServiceFeignExceptionHandle implements TProjectServiceFeign {
    @Override
    public AppResponse<TReturn> returnInfo(Integer returnId) {
        AppResponse<TReturn> resp = AppResponse.fail(null);
        resp.setMsg("调用远程服务SCW-PROJECT【根据回报id查询回报】异常");
        return resp;
    }
}
