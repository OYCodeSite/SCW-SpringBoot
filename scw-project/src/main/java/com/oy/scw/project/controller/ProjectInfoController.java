package com.oy.scw.project.controller;

import com.oy.scw.project.bean.*;
import com.oy.scw.project.component.OssTemplate;
import com.oy.scw.project.service.Feign.MemberServiceFeign;
import com.oy.scw.project.service.ProjectInfoService;
import com.oy.scw.project.vo.resp.*;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import io.swagger.annotations.Api;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Api(tags = "1-项目信息模块")
@RequestMapping("/project")
@RestController
public class ProjectInfoController {

	@Autowired
	ProjectInfoService projectInfoService;

	@Autowired
	OssTemplate ossTemplate;

	@Autowired
	MemberServiceFeign memberServiceFeign;

	@ApiOperation("1-获取项目信息详细")
	@GetMapping("/details/info/{projectId}")
	public AppResponse<ProjectDetailVo> detailsInfo(@PathVariable("projectId") Integer projectId){
		TProject p = projectInfoService.getProjectInfo(projectId);
		ProjectDetailVo projectVo = new ProjectDetailVo();


		// 1、查出这个项目的所有图片
		List<TProjectImages> projectImages = projectInfoService.getProjectImages(p.getId());

		for (TProjectImages tProjectImages : projectImages) {
			if (tProjectImages.getImgtype() == 0) {
				projectVo.setHeaderImage(tProjectImages.getImgurl());
			} else {
				List<String> detailsImage = projectVo.getDetailsImage();
				detailsImage.add(tProjectImages.getImgurl());
			}
		}

		// 2、项目的所有支持档位；
		List<TReturn> returns = projectInfoService.getProjectReturns(p.getId());
		projectVo.setProjectReturns(returns);

		BeanUtils.copyProperties(p, projectVo);
		return AppResponse.ok(projectVo);
	}

	@ApiOperation("2-获取项目回报列表")
	@GetMapping("/details/returns/{projectId}")
	public AppResponse<List<TReturn>> detailsReturn(@PathVariable("projectId") Integer projectId) {
		List<TReturn> returns = projectInfoService.getProjectReturns(projectId);
		return AppResponse.ok(returns);
	}

	@ApiOperation("3-获取项目某个回报档位信息")
	@GetMapping("/confim/returns/info/{projectId}/{returnId}")
	public AppResponse<ReturnPayConfirmVo> returnInfo(@PathVariable("projectId") Integer projectId,@PathVariable("returnId") Integer returnId) {

		ReturnPayConfirmVo vo = new ReturnPayConfirmVo();

		//部分1: 回报数据
		TReturn tReturn = projectInfoService.getProjectReturnById(returnId);
		vo.setReturnId(tReturn.getId());
		vo.setReturnContent(tReturn.getContent());
		vo.setNum(1);
		vo.setPrice(tReturn.getSupportmoney());
		vo.setFreight(tReturn.getFreight());
		vo.setSignalpurchase(tReturn.getSignalpurchase());
		vo.setPurchase(tReturn.getPurchase());

		// 部分2: 项目数据
		TProject project = projectInfoService.getProjectInfo(projectId);
		vo.setProjectId(project.getId());
		vo.setProjectName(project.getName());
		vo.setProjectRemark(project.getRemark());

		// 部分3: 发起人数据
		Integer memberid = project.getMemberid();
		AppResponse<TMember> resp = memberServiceFeign.getMebmerById(memberid); // 调用远程获取会员
		TMember member = resp.getData();


		vo.setMemberId(member.getId());
		vo.setMemberName(member.getLoginacct());

		vo.setTotalPrice(new BigDecimal(vo.getNum() * vo.getPrice() + vo.getFreight()));
		return AppResponse.ok(vo);
	}

	@ApiOperation("7-获取项目某个回报档位信息")
	@GetMapping("/details/returns/info/{returnId}")
	public AppResponse<TReturn> returnInfo(@PathVariable("returnId") Integer returnId) {
		TReturn tReturn = projectInfoService.getProjectReturnById(returnId);
		return AppResponse.ok(tReturn);
	}

	@ApiOperation("4-获取系统所有的项目分类")
	@GetMapping("/types")
	public AppResponse<List<TType>> types() {
		List<TType> types = projectInfoService.getProjectTypes();
		return AppResponse.ok(types);
	}

	@ApiOperation("5-获取系统所有的项目标签")
	@GetMapping("/tags")
	public AppResponse<List<TTag>> tags() {
		List<TTag> tags = projectInfoService.getAllProjectTags();
		return AppResponse.ok(tags);
	}

	@ApiOperation("6-获取系统所有的项目")
	@GetMapping("/all")
	public AppResponse<List<ProjectVo>> all() {
		// 1、分步查询，先查出所有项目
		// 2、在查询这些项目图片
		List<ProjectVo> prosVo = new ArrayList<>();

		List<TProject> pros = projectInfoService.getAllProjects();

		for (TProject tProject : pros) {
			Integer id = tProject.getId();
			List<TProjectImages> images = projectInfoService.getProjectImages(id);
			ProjectVo projectVo = new ProjectVo();
			BeanUtils.copyProperties(tProject, projectVo);


			for(TProjectImages tProjectImages : images){
				if(tProjectImages.getImgtype() == 0){
					projectVo.setHeaderImage(tProjectImages.getImgurl());
				}
			}

			prosVo.add(projectVo);
		}

		return AppResponse.ok(prosVo);
	}

	@ApiOperation("文件上传功能")
	@PostMapping("/upload")
	public AppResponse<Map<String, Object>> upload(@RequestParam("file") MultipartFile[] file) throws IOException {
		Map<String, Object> map = new HashMap<>();

		List<String> list = new ArrayList<>();

		if(file != null && file.length >0){
			for(MultipartFile item : file){
				if(!item.isEmpty()){
					String upload = ossTemplate.upload(item.getOriginalFilename(), item.getInputStream());
					list.add(upload);
				}
			}
		}
		map.put("urls",list);
		log.debug("ossTemplate信息: {}, 文件上传成功访问路径：{}",ossTemplate, list);

		return AppResponse.ok(map);
	}

	
}
