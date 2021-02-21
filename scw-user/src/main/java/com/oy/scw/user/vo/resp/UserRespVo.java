package com.oy.scw.user.vo.resp;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @Author OY
 * @Date 2021/2/12
 */
@ApiModel
@Data
public class UserRespVo implements Serializable {

    @ApiModelProperty("访问令牌，请妥善保管，以后每次请求都要带上")
   // 令牌 登录以后会分配当前用户一个临时令牌值。以后对系统的任何访问必须携带这个令牌，否则，拒绝访问
    private String accessToken;

    private String loginacct;

    private String username;

    private String email;

    private String authstatus = "0";

    private String usertype;

    private String realname;

    private String cardnum;

    private String accttype;


}
