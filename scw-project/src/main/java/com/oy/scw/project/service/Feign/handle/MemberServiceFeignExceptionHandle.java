package com.oy.scw.project.service.Feign.handle;

import com.oy.scw.project.service.Feign.MemberServiceFeign;
import com.oy.scw.project.vo.resp.AppResponse;
import com.oy.scw.project.vo.resp.TMember;
import org.springframework.stereotype.Component;


/**
 * @Author OY
 * @Date 2021/2/18
 */
@Component
public class MemberServiceFeignExceptionHandle implements MemberServiceFeign {
    @Override
    public AppResponse<TMember> getMebmerById(Integer id) {
        AppResponse<TMember> resp = AppResponse.fail(null);
        resp.setMsg("远程调用服务【根据id获取用户/发起人信息】失败");
        return resp;
    }
}
