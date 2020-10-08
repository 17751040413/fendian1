package com.wowoniu.fendian.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.sql.Timestamp;

@ApiModel("分销用户")
public class DistributionUser implements Serializable {

    @ApiModelProperty("主键ID")
    private String id;
    @ApiModelProperty("用户Id")
    private String buyerId;
    @ApiModelProperty("昵称")
    private String name;
    @ApiModelProperty("头像地址")
    private String url;
    @ApiModelProperty("创建时间")
    private Timestamp time;
    @ApiModelProperty("手机号")
    private String phone;
    @ApiModelProperty("分销金额（分）")
    private int money;
    @ApiModelProperty("发券数量")
    private int number;
    @ApiModelProperty("分销活动ID")
    private String distributionId;
    @ApiModelProperty("销售额")
    private int price;

    @ApiModelProperty("联盟ID")
    private String allianceId;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getBuyerId() {
        return buyerId;
    }

    public void setBuyerId(String buyerId) {
        this.buyerId = buyerId;
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

    public Timestamp getTime() {
        return time;
    }

    public void setTime(Timestamp time) {
        this.time = time;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getDistributionId() {
        return distributionId;
    }

    public void setDistributionId(String distributionId) {
        this.distributionId = distributionId;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getAllianceId() {
        return allianceId;
    }

    public void setAllianceId(String allianceId) {
        this.allianceId = allianceId;
    }
}
