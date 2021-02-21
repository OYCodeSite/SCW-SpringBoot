package com.oy.scw.webui.vo.resp;

import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author OY
 * @Date 2021/2/17
 */
@Data
public class ProjectVo implements Serializable {

    private Integer memberid;// 会员id

    private Integer id;// 项目id

    private String name;// 项目名称
    private String remark;// 项目简介

    private String headerImage;// 项目头部图片
}
