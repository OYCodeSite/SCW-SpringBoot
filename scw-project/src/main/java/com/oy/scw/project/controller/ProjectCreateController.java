package com.oy.scw.project.controller;

import com.alibaba.fastjson.JSON;
import com.oy.scw.enums.ProjectStatusEnume;
import com.oy.scw.project.bean.TReturn;
import com.oy.scw.project.component.OssTemplate;
import com.oy.scw.project.constant.ProjectConstant;
import com.oy.scw.project.service.TProjectService;
import com.oy.scw.project.vo.req.BaseVo;
import com.oy.scw.project.vo.req.ProjectBaseInfoVo;
import com.oy.scw.project.vo.req.ProjectRedisStorageVo;
import com.oy.scw.project.vo.req.ProjectReturnVo;
import com.oy.scw.project.vo.resp.AppResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;


import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Slf4j
@Api(tags = "项目发起模块")
@RequestMapping("/project/create")
@RestController
public class ProjectCreateController {

    @Autowired
    OssTemplate ossTemplate;

    @Autowired
    StringRedisTemplate redisTemplate;

    @Autowired
    TProjectService projectService;

    @ApiOperation(value = "1-项目初始创建")
    @PostMapping("/init")
    public AppResponse<Object> init(BaseVo vo) {

        try {
            String accessToken = vo.getAccessToken();

            // 判断accessToken 是否为空
            if(StringUtils.isEmpty(accessToken)){
                AppResponse<Object> resp = AppResponse.fail(null);
                resp.setMsg("请求必须提供assessToken值");
                return resp;
            }

            // 获取redis中的accessToken 值
            String memberId = redisTemplate.opsForValue().get(accessToken);

            // 判断 member 是否为空
            if(StringUtils.isEmpty(memberId)){
                AppResponse resp = AppResponse.fail(null);
                resp.setMsg("请先登录系统，在发布项目");
                return resp;
            }

            ProjectRedisStorageVo bigVo = new ProjectRedisStorageVo();

            BeanUtils.copyProperties(vo, bigVo);

            String projectToken = UUID.randomUUID().toString().replace("-", "");
            bigVo.setProjectToken(projectToken);

            // fastjson 组件
            String bigStr = JSON.toJSONString(bigVo);

            redisTemplate.opsForValue().set(ProjectConstant.TEMP_PROJECT_PREFIX + projectToken, bigStr);

            // jackjson
            log.debug("大VO数据:{}",bigVo);

            return AppResponse.ok(bigVo);
        } catch (Exception e) {
            e.printStackTrace();
            return AppResponse.fail(null);
        }
    }

    @ApiOperation(value = "2-项目基本信息")
    @PostMapping("/baseinfo")
    public AppResponse<Object> baseinfo(ProjectBaseInfoVo vo) {

        try {
            // 1.验证用户是否登录
            String accessToken = vo.getAccessToken();

            if(StringUtils.isEmpty(accessToken)){
                AppResponse<Object> resp = AppResponse.fail(null);
                resp.setMsg("请求必须提供assessToken值");
                return resp;
            }

            String memberId = redisTemplate.opsForValue().get(accessToken);

            if(StringUtils.isEmpty(memberId)){
                AppResponse<Object> resp = AppResponse.fail(null);
                resp.setMsg("请先登录系统,在发布项目");
                return resp;
            }

            // 2.从Redis中获取 bigVO数据，将小vo封装到大VO中
            String bigStr = redisTemplate.opsForValue().get(ProjectConstant.TEMP_PROJECT_PREFIX + vo.getProjectToken());
            ProjectRedisStorageVo bigVo = JSON.parseObject(bigStr,ProjectRedisStorageVo.class);

            BeanUtils.copyProperties(vo,bigVo);

            bigStr = JSON.toJSONString(bigVo);

            redisTemplate.opsForValue().set(ProjectConstant.TEMP_PROJECT_PREFIX+vo.getProjectToken(),bigStr);

            log.debug("大VO数据:{}",bigVo);

            return AppResponse.ok(bigVo);
        } catch (Exception e) {
            e.printStackTrace();
            log.error("表单处理失败",e.getMessage());
            return AppResponse.fail(null);
        }
    }

    @ApiOperation(value = "3-添加项目回报档位")
    @PostMapping("/return")
    public AppResponse<Object> returnDetail(@RequestBody List<ProjectReturnVo> pro) {

        try {
            // 1. 验证用户是否登录
            String accessToken = pro.get(0).getAccessToken();

            if(StringUtils.isEmpty(accessToken)){
                AppResponse resp = AppResponse.fail(null);
                resp.setMsg("请必须提供assessToken值");
                return resp;
            }

            String memberId = redisTemplate.opsForValue().get(accessToken);

            if(StringUtils.isEmpty(memberId)){
                AppResponse resp = AppResponse.fail(null);
                resp.setMsg("请先登录系统，在来发布项目");
                return resp;
            }

            // 2.从Redis中获取 bigVo数据，将小vo封装到大Vo中
            String bigStr = redisTemplate.opsForValue().get(ProjectConstant.TEMP_PROJECT_PREFIX + pro.get(0).getProjectToken());
            ProjectRedisStorageVo bigVo = JSON.parseObject(bigStr, ProjectRedisStorageVo.class);

            List<TReturn> projectReturns = bigVo.getProjectReturns();
            for (ProjectReturnVo projectReturnVo : pro) {
                TReturn returnObj = new TReturn();
                BeanUtils.copyProperties(projectReturnVo,returnObj);
                projectReturns.add(returnObj);
            }

            bigStr = JSON.toJSONString(bigVo);

            redisTemplate.opsForValue().set(ProjectConstant.TEMP_PROJECT_PREFIX+pro.get(0).getProjectToken(),bigStr);

            log.debug("大VO数据：{}",bigStr);

            return AppResponse.ok(bigStr);
        } catch (BeansException e) {
            e.printStackTrace();
            log.error("表单处理失败",e.getMessage());
            return AppResponse.fail(null);
        }
    }


    @ApiOperation(value = "4-上传图片")
    @PostMapping("/upload")
    public AppResponse<Object> upload(@RequestParam("uploadfile")MultipartFile[] files) {

        ArrayList<String> filepathList = new ArrayList<>();

        try {
            for(MultipartFile multipartFile :files){
                String filename = multipartFile.getOriginalFilename();

                filename = UUID.randomUUID().toString().replace("-","")+"_" + filename;

                String filepath = ossTemplate.upload(filename, multipartFile.getInputStream());
                filepathList.add(filepath);

            }

            log.debug("上传文件路径={}",filepathList);

            return AppResponse.ok("ok");
        } catch (IOException e) {
            e.printStackTrace();
            log.debug("上传文件出现异常");
            return AppResponse.fail(null);
        }
    }


    @ApiOperation(value = "5-项目提交审核申请")
    @PostMapping("/submit")
    public AppResponse<Object> submit(String accessToken, String projectToken, String ops) {

        try {
            // 1.验证用户是否登录
            if(StringUtils.isEmpty(accessToken)){
                AppResponse resp = AppResponse.fail(null);
                resp.setMsg("请求必须提供assessToken");
                return resp;
            }

            String member = redisTemplate.opsForValue().get(accessToken);

            if(StringUtils.isEmpty(member)){
                AppResponse resp = AppResponse.fail(null);
                resp.setMsg("请先登录系统,在发布项目!");
                return resp;
            }

            if("0".equals(ops)){  // 保存草稿

                projectService.saveProject(accessToken, projectToken, ProjectStatusEnume.DRAFT.getCode());

                return AppResponse.ok("ok");
            }else if("1".equals(ops)){
                projectService.saveProject(accessToken,projectToken,ProjectStatusEnume.SUBMIT_AUTH.getCode());
                return AppResponse.ok("ok");
            }else{
                log.error("请求方式不支持");
                return AppResponse.fail("请求方式不支持");
            }
        } catch (Exception e) {
            e.printStackTrace();
            log.error("项目操作失败-{}",e.getMessage());
            return AppResponse.fail(null);
        }


    }

//	@ApiOperation(value = "删除项目回报档位")
//	@DeleteMapping("/return")
//	public AppResponse<Object> deleteReturnDetail() {
//		return AppResponse.ok("ok");
//	}

//	@ApiOperation(value = "确认项目法人信息")
//	@PostMapping("/confirm/legal")
//	public AppResponse<Object> legal() {
//		return AppResponse.ok("ok");
//	}

//	@ApiOperation(value = "项目草稿保存")
//	@PostMapping("/tempsave")
//	public AppResponse<Object> tempsave() {
//		return AppResponse.ok("ok");
//	}



}
