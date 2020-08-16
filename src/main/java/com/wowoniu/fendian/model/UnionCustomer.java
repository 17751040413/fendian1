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

    private static final long serialVersionUID = 1L;

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