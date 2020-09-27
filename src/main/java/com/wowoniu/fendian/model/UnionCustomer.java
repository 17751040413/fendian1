package com.wowoniu.fendian.model;

import java.io.Serializable;


/**
 * union_customer
 * @author 
 */

public class UnionCustomer implements Serializable {
    /**
     * 联盟顾客表
     */
    private String id;

    /**
     * 联盟id
     */
    private String unionId;

    /**
     * 联盟名
     */
    private String unionName;

    /**
     * 联盟logo
     */
    private String unionLogo;

    /**
     * 用户id
     */
    private String userId;

    /**
     * 用户昵称
     */
    private String userName;

    /**
     * 用户账号
     */
    private String userLoginName;

    /**
     * 用户头像
     */
    private String userImg;

    /**
     * 推荐人id
     */
    private String recommendId;

    /**
     * 创建时间
     */
    private String createTime;

    /**
     * 最近领取时间
     */
    private Integer receiveTime;

    /**
     * 最近使用时间
     */
    private Integer isUseTime;

    /**
     * 是否添加盟主微信
     */
    private Integer ifWechat;

    private static final long serialVersionUID = 1L;


    public Integer getIfWechat() {
        return ifWechat;
    }

    public void setIfWechat(Integer ifWechat) {
        this.ifWechat = ifWechat;
    }

    public String getUserLoginName() {
        return userLoginName;
    }

    public void setUserLoginName(String userLoginName) {
        this.userLoginName = userLoginName;
    }

    public Integer getReceiveTime() {
        return receiveTime;
    }

    public void setReceiveTime(Integer receiveTime) {
        this.receiveTime = receiveTime;
    }

    public Integer getIsUseTime() {
        return isUseTime;
    }

    public void setIsUseTime(Integer isUseTime) {
        this.isUseTime = isUseTime;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUnionId() {
        return unionId;
    }

    public void setUnionId(String unionId) {
        this.unionId = unionId;
    }

    public String getUnionName() {
        return unionName;
    }

    public void setUnionName(String unionName) {
        this.unionName = unionName;
    }

    public String getUnionLogo() {
        return unionLogo;
    }

    public void setUnionLogo(String unionLogo) {
        this.unionLogo = unionLogo;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserImg() {
        return userImg;
    }

    public void setUserImg(String userImg) {
        this.userImg = userImg;
    }

    public String getRecommendId() {
        return recommendId;
    }

    public void setRecommendId(String recommendId) {
        this.recommendId = recommendId;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }
}