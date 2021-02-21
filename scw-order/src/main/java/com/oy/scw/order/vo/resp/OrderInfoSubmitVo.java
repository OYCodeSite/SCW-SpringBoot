package com.oy.scw.order.vo.resp;

import lombok.Data;

import java.io.Serializable;

@Data
public class OrderInfoSubmitVo implements Serializable {
	
    private String accessToken;

    private Integer projectid;

    private Integer returnid;

    private Integer rtncount;

    private String address;

    private Byte invoice;

    private String invoictitle;

    private String remark;

}
