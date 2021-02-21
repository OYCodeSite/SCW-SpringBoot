package com.oy.scw.webui.vo.resp;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * @Author OY
 * @Date 2021/2/19
 */
@ApiModel
public class UserAddressVo implements Serializable {
    @ApiModelProperty("地址id")
    private Integer id = 1;

    @ApiModelProperty("会员地址")
    private String address = "牙齿大街1号";

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
