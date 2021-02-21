package com.oy.scw.order.vo.resp;

import lombok.Data;

import java.io.Serializable;

@Data
public class TReturn implements Serializable {
    private Integer id;

    private Integer projectid;

    private Byte type;

    private Integer supportmoney;

    private String content;

    private Integer count;

    private Integer signalpurchase;

    private Integer purchase;

    private Integer freight;

    private Byte invoice;

    private Integer rtndate;

}