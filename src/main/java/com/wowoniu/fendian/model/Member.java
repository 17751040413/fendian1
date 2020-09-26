package com.wowoniu.fendian.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

@ApiModel("会员")
public class Member implements Serializable {
    @ApiModelProperty("主键ID")
    private String id;
    @ApiModelProperty("商家ID")
    private String userId;
    @ApiModelProperty("会员ID")
    private String buyerId;
    @ApiModelProperty("余额")
    private int price;
    @ApiModelProperty("手机号")
    private int phone;
}
