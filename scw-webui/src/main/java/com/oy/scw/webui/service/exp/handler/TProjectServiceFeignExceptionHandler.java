package com.oy.scw.webui.service.exp.handler;

import com.oy.scw.project.vo.resp.AppResponse;
import com.oy.scw.webui.service.TProjectServiceFeign;
import com.oy.scw.webui.vo.resp.ProjectDetailVo;
import com.oy.scw.webui.vo.resp.ProjectVo;
import com.oy.scw.webui.vo.resp.ReturnPayConfirmVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @Author OY
 * @Date 2021/2/17
 */
@Slf4j
@Component
public class TProjectServiceFeignExceptionHandler implements TProjectServiceFeign {

    public AppResponse<List<ProjectVo>> all(){
        AppResponse resp = AppResponse.fail(null);
        resp.setMsg("调用远程服务【查询首页热点项目】失败");
        log.error("调用远程服务【查询首页热点项目】失败");
        return resp;
    }

    @Override
    public AppResponse<ProjectDetailVo> detailsInfo(Integer projectId) {
        AppResponse resp = AppResponse.fail(null);
        resp.setMsg("调用远程服务【查询项目详情】失败");
        log.error("调用远程服务【查询项目详情】失败");
        return resp;
    }

    @Override
    public AppResponse<ReturnPayConfirmVo> returnInfo(Integer projectId, Integer returnId) {
        AppResponse<ReturnPayConfirmVo> resp = AppResponse.fail(null);
        resp.setMsg("调用远程服务【查询确认回报信息】失败");
        log.error("调用远程服务【查询确认回报信息】失败");
        return resp;
    }

}
