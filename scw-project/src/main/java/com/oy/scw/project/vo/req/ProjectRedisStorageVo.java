package com.oy.scw.project.vo.req;

import com.oy.scw.project.bean.TReturn;
import lombok.Data;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author OY
 * @Date 2021/2/14
 */
@ToString
@Data
public class ProjectRedisStorageVo extends BaseVo{

    // 项目的临时token
    private String projectToken;

    // 项目的分类id
    private List<Integer> typeids = new ArrayList<Integer>();;

    //项目的标签id
    private List<Integer> tagids = new ArrayList<Integer>();;

    private String name;
    private String remark;
    private Long money;
    private Integer day;

    // 项目头部图片
    private String headerImage;
    // 项目详细图片
    private List<String> detailsImage = new ArrayList<String>();

    //扩展:发起人信息：自我介绍，详细自我介绍，联系电话，客服电话

    // 项目回报
    private List<TReturn> projectReturns = new ArrayList<>();

    // 确认信息（请填写易付宝企业账号用于收款及身份核实）
}
