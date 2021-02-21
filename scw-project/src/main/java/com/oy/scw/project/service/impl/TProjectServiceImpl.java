package com.oy.scw.project.service.impl;

import com.alibaba.fastjson.JSON;
import com.oy.scw.project.bean.*;
import com.oy.scw.project.constant.ProjectConstant;
import com.oy.scw.project.mapper.*;
import com.oy.scw.project.service.TProjectService;
import com.oy.scw.project.vo.req.ProjectRedisStorageVo;
import com.oy.scw.util.AppDateUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @Author OY
 * @Date 2021/2/15
 */
@Slf4j
@Service
public class TProjectServiceImpl implements TProjectService {

    @Autowired
    StringRedisTemplate redisTemplate;

    @Autowired
    TProjectMapper projectMapper;

    @Autowired
    TProjectImagesMapper projectImagesMapper;

    @Autowired
    TReturnMapper returnMapper;

    @Autowired
    TProjectTypeMapper projectTypeMapper;

    @Autowired
    TProjectTagMapper projectTagMapper;

    @Transactional
    @Override
    public void saveProject(String accessToken, String projectToken, byte code) {

        String memberId = redisTemplate.opsForValue().get(accessToken);

        //1.从redis中获取 bigVo数据

        String bigStr = redisTemplate.opsForValue().get(ProjectConstant.TEMP_PROJECT_PREFIX + projectToken);

        ProjectRedisStorageVo bigVo = JSON.parseObject(bigStr, ProjectRedisStorageVo.class);

        //2. 保存项目
        TProject project = new TProject();

        project.setName(bigVo.getName());
        project.setRemark(bigVo.getRemark());
        project.setMoney(bigVo.getMoney());
        project.setDay(bigVo.getDay());
        project.setStatus(code+"");
        project.setMemberid(Integer.parseInt(memberId));
        project.setCreatedate(AppDateUtils.getFormatTime());

        // 主键回填 useGeneratedKeys="true" keyProperty="id"
        projectMapper.insertSelective(project);

        Integer projectId = project.getId();
        log.debug("保存项目id={}",projectId);

        // 3.保存图片
        TProjectImages projectImage = new TProjectImages();
        projectImage.setProjectid(projectId);
        projectImage.setImgurl(bigVo.getHeaderImage());
        projectImage.setImgtype((byte)0);
        projectImagesMapper.insertSelective(projectImage);

        List<String> detailsImage = bigVo.getDetailsImage();

        for(String imgPath : detailsImage){
            TProjectImages pi = new TProjectImages();
            pi.setProjectid(projectId);
            pi.setImgurl(imgPath);
            pi.setImgtype((byte)1);
            projectImagesMapper.insertSelective(pi);
        }

        // 4.保存回报
        List<TReturn> projectReturns = bigVo.getProjectReturns();
        for(TReturn tReturn : projectReturns){
            tReturn.setProjectid(projectId);
            returnMapper.insertSelective(tReturn);
        }

        // 5.保存项目和分类关系
        List<Integer> typeids = bigVo.getTypeids();
        for (Integer typeid : typeids) {
            TProjectType pt = new TProjectType();
            pt.setProjectid(projectId);
            pt.setTypeid(typeid);
            projectTypeMapper.insertSelective(pt);
        }

        // 6.保存项目和标签关系
        List<Integer> tagids = bigVo.getTagids();
        for(Integer tagId : tagids){
            TProjectTag pt = new TProjectTag();
            pt.setProjectid(projectId);
            pt.setTagid(tagId);
            projectTagMapper.insertSelective(pt);
        }

        //7.保存发起人（省略）

        //8.保存法人（省略）

        // 9.清理redis
//        redisTemplate.delete(ProjectConstant.TEMP_PROJECT_PREFIX+projectToken);
    }
}
