package com.wowoniu.fendian.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.sql.Timestamp;

/**
 * @author lastwhisper
 * @desc
 * @email gaojun56@163.com
 */
@ApiModel("微信用户")
public class User {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty("open_id")
    private String openId;
    @ApiModelProperty("skey")
    private String skey;
    @ApiModelProperty("创建时间")
    private Timestamp createTime;
    @ApiModelProperty("最后登录时间")
    private Timestamp lastVisitTime;
    @ApiModelProperty("session_key")
    private String sessionKey;
    @ApiModelProperty("手机号")
    private String phone;
    @ApiModelProperty("市")
    private String city;
    @ApiModelProperty("省")
    private String province;
    @ApiModelProperty("国")
    private String country;
    @ApiModelProperty("头像")
    private String avatarUrl;
    @ApiModelProperty("性别")
    private Integer gender;
    @ApiModelProperty("网名")
    private String nickName;
    @ApiModelProperty("邀请人数")
    private Integer inviterNum;
    @ApiModelProperty("最后消费")
    private Integer lastBuy;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }

    public String getSkey() {
        return skey;
    }

    public void setSkey(String skey) {
        this.skey = skey;
    }

    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    public Timestamp getLastVisitTime() {
        return lastVisitTime;
    }

    public void setLastVisitTime(Timestamp lastVisitTime) {
        this.lastVisitTime = lastVisitTime;
    }

    public String getSessionKey() {
        return sessionKey;
    }

    public void setSessionKey(String sessionKey) {
        this.sessionKey = sessionKey;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    public Integer getGender() {
        return gender;
    }

    public void setGender(Integer gender) {
        this.gender = gender;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public Integer getLastBuy() {
        return lastBuy;
    }

    public void setLastBuy(Integer lastBuy) {
        this.lastBuy = lastBuy;
    }

    public Integer getInviterNum() {
        return inviterNum;
    }

    public void setInviterNum(Integer inviterNum) {
        this.inviterNum = inviterNum;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}

