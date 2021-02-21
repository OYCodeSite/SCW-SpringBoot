package com.oy.scw.project.service.impl;

import com.netflix.discovery.converters.Auto;
import com.oy.scw.project.bean.*;
import com.oy.scw.project.mapper.*;
import com.oy.scw.project.service.ProjectInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author OY
 * @Date 2021/2/17
 */
@Service
public class ProjectInfoServiceImpl implements ProjectInfoService {

    @Autowired
    TProjectMapper projectMapper;

    @Autowired
    TProjectImagesMapper imageMapper;

    @Autowired
    TReturnMapper returnMapper;

    @Autowired
    TTypeMapper typeMapper;

    @Autowired
    TTagMapper tagMapper;

    @Override
    public List<TType> getProjectTypes() {
        return typeMapper.selectByExample(null);
    }

    @Override
    public List<TTag> getAllProjectTags() {
        return tagMapper.selectByExample(null);
    }

    @Override
    public TProject getProjectInfo(Integer projectId) {
        TProject project = projectMapper.selectByPrimaryKey(projectId);
        return project;
    }

    @Override
    public List<TReturn> getProjectReturns(Integer projectId) {
        TReturnExample example = new TReturnExample();
        example.createCriteria().andProjectidEqualTo(projectId);
        return returnMapper.selectByExample(example);
    }

    @Override
    public List<TProject> getAllProjects() {
        return projectMapper.selectByExample(null);
    }

    @Override
    public List<TProjectImages> getProjectImages(Integer id) {
        TProjectImagesExample example = new TProjectImagesExample();
        example.createCriteria().andProjectidEqualTo(id);
        return imageMapper.selectByExample(example);
    }

    @Override
    public TReturn getProjectReturnById(Integer retId) {
        return returnMapper.selectByPrimaryKey(retId);
    }
}
