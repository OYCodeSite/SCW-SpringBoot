package com.oy.scw.project.service;

import com.oy.scw.project.bean.*;

import java.util.List;

public interface ProjectInfoService {
    TProject getProjectInfo(Integer projectId);

    List<TProjectImages> getProjectImages(Integer id);


    List<TReturn> getProjectReturns(Integer id);

    TReturn getProjectReturnById(Integer returnId);

    List<TType> getProjectTypes();

    List<TTag> getAllProjectTags();

    List<TProject> getAllProjects();
}
