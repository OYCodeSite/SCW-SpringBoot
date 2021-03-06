package com.oy.scw.project.vo.req;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

/**
 * @Author OY
 * @Date 2021/2/15
 */
@Data
@ToString
public class ProjectReturnVo extends BaseVo {

    // 项目的临时token
    private String projectToken;

    @ApiModelProperty(value = "回报类型： 0-虚拟回报   1- 实物回报", required = true)
    private String type;

    @ApiModelProperty(value = "支持金额", required = true)
    private Integer supportmoney;

    @ApiModelProperty(value = "回报内容", required = true)
    private String content;

    @ApiModelProperty(value = "回报数量", required = true)
    private Integer count;

    @ApiModelProperty(value = "单笔限购", required = true)
    private Integer signalpurchase;

    @ApiModelProperty(value = "限购数量", required = true)
    private Integer purchase;

    @ApiModelProperty(value = "运费", required = true)
    private Integer freight;

    @ApiModelProperty(value = "发票  0-不开发票  1-开发票", required = true)
    private String invoice;

    @ApiModelProperty(value = "回报时间，天数", required = true)
    private Integer rtndate;

}
