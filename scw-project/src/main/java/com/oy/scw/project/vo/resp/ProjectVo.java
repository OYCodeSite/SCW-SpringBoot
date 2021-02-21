package com.oy.scw.project.vo.resp;

import com.oy.scw.project.bean.TReturn;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 *  首页热点项目
 * @Author OY
 * @Date 2021/2/17
 */
@Data
@ToString
public class ProjectVo implements Serializable {

    private Integer memberId; // 会员id

    private Integer id;// 项目id

    private String name; // 项目名称
    private String remark; //项目简介

    private String headerImage; // 项目头部图片

}
