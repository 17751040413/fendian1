package com.wowoniu.fendian.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.sql.Timestamp;

@ApiModel("返利记录")
public class RebateRecord implements Serializable {
    @ApiModelProperty("主键ID")
    private String id;
    @ApiModelProperty("昵称")
    private String name;
    @ApiModelProperty("头像地址")
    private String url;
    @ApiModelProperty("返利用户")
    private String buyerId;
    @ApiModelProperty("记录时间")
    private Timestamp time;
    @ApiModelProperty("返利金额")
    private String money;

    @ApiModelProperty("活动ID")
    private String rebateId;
    @ApiModelProperty("订单号")
    private String code;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getBuyerId() {
        return buyerId;
    }

    public void setBuyerId(String buyerId) {
        this.buyerId = buyerId;
    }

    public Timestamp getTime() {
        return time;
    }

    public void setTime(Timestamp time) {
        this.time = time;
    }

    public String getMoney() {
        return money;
    }

    public void setMoney(String money) {
        this.money = money;
    }

    public String getRebateId() {
        return rebateId;
    }

    public void setRebateId(String rebateId) {
        this.rebateId = rebateId;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
