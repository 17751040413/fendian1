package com.wowoniu.fendian.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.sql.Timestamp;

@ApiModel("会员")
public class Member implements Serializable {
    @ApiModelProperty("主键ID")
    private String id;
    @ApiModelProperty("商家ID")
    private String userId;
    @ApiModelProperty("会员ID")
    private String buyerId;
    @ApiModelProperty("用户Skey")
    private String skey;
    @ApiModelProperty("余额")
    private int price;
    @ApiModelProperty("手机号")
    private String phone;
    @ApiModelProperty("到期时间")
    private Timestamp endTime;
    @ApiModelProperty("头像地址")
    private String url;
    @ApiModelProperty("姓名")
    private String name;
    @ApiModelProperty("邀请人数")
    private int number;
    @ApiModelProperty("创建时间")
    private Timestamp createTime;
    @ApiModelProperty("昵称")
    private String nickName;
    @ApiModelProperty("等级")
    private String level;
    @ApiModelProperty("性别")
    private Integer gender;
    @ApiModelProperty("备注")
    private String remark;
    @ApiModelProperty("最后消费")
    private String lastBuy;
    @ApiModelProperty("等级名称")
    private String levelName;

    public String getLastBuy() {
        return lastBuy;
    }

    public void setLastBuy(String lastBuy) {
        this.lastBuy = lastBuy;
    }

    public String getLevelName() {
        return levelName;
    }

    public void setLevelName(String levelName) {
        this.levelName = levelName;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getBuyerId() {
        return buyerId;
    }

    public void setBuyerId(String buyerId) {
        this.buyerId = buyerId;
    }

    public String getSkey() {
        return skey;
    }

    public void setSkey(String skey) {
        this.skey = skey;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Timestamp getEndTime() {
        return endTime;
    }

    public void setEndTime(Timestamp endTime) {
        this.endTime = endTime;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public Integer getGender() {
        return gender;
    }

    public void setGender(Integer gender) {
        this.gender = gender;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
