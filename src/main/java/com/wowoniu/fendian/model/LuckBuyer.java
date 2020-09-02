package com.wowoniu.fendian.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.sql.Timestamp;

@ApiModel("中奖记录")
public class LuckBuyer implements Serializable {

    @ApiModelProperty("主键ID")
    private String id;
    @ApiModelProperty("抽奖ID")
    private String luckId;
    @ApiModelProperty("兑换码")
    private String code;
    @ApiModelProperty("有效期开始时间")
    private Timestamp startTime;
    @ApiModelProperty("有效期结束时间")
    private Timestamp endTime;
    @ApiModelProperty("中奖时间")
    private Timestamp createTime;
    @ApiModelProperty("中奖人ID")
    private String buyerId;
    @ApiModelProperty("状态：0未兑换；1已兑换;2过期")
    private String state;
    @ApiModelProperty("奖项名称")
    private String name;
    @ApiModelProperty("使用范围")
    private String range;
    @ApiModelProperty("奖品名称")
    private String prizeName;
    @ApiModelProperty("奖品图片")
    private String pictureUrl;
    @ApiModelProperty("店铺名称")
    private String shopName;
    @ApiModelProperty("店铺地址")
    private String shopAddress;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLuckId() {
        return luckId;
    }

    public void setLuckId(String luckId) {
        this.luckId = luckId;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Timestamp getStartTime() {
        return startTime;
    }

    public void setStartTime(Timestamp startTime) {
        this.startTime = startTime;
    }

    public Timestamp getEndTime() {
        return endTime;
    }

    public void setEndTime(Timestamp endTime) {
        this.endTime = endTime;
    }

    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    public String getBuyerId() {
        return buyerId;
    }

    public void setBuyerId(String buyerId) {
        this.buyerId = buyerId;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRange() {
        return range;
    }

    public void setRange(String range) {
        this.range = range;
    }

    public String getPrizeName() {
        return prizeName;
    }

    public void setPrizeName(String prizeName) {
        this.prizeName = prizeName;
    }

    public String getPictureUrl() {
        return pictureUrl;
    }

    public void setPictureUrl(String pictureUrl) {
        this.pictureUrl = pictureUrl;
    }

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    public String getShopAddress() {
        return shopAddress;
    }

    public void setShopAddress(String shopAddress) {
        this.shopAddress = shopAddress;
    }
}
