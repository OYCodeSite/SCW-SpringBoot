package com.oy.scw.webui.service;

import com.oy.scw.project.vo.resp.AppResponse;
import com.oy.scw.webui.service.exp.handler.TProjectServiceFeignExceptionHandler;
import com.oy.scw.webui.vo.resp.ProjectDetailVo;
import com.oy.scw.webui.vo.resp.ProjectVo;
import com.oy.scw.webui.vo.resp.ReturnPayConfirmVo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(value = "SCW-PROJECT", fallback = TProjectServiceFeignExceptionHandler.class)
public interface TProjectServiceFeign {

    @GetMapping("/project/all")
    public AppResponse<List<ProjectVo>> all() ;

    @GetMapping("/project/details/info/{projectId}")
    public AppResponse<ProjectDetailVo> detailsInfo(@PathVariable("projectId") Integer projectId);

    @GetMapping("/project/confim/returns/info/{projectId}/{returnId}")
    public AppResponse<ReturnPayConfirmVo> returnInfo(@PathVariable("projectId") Integer projectId, @PathVariable("returnId") Integer returnId);
    }
