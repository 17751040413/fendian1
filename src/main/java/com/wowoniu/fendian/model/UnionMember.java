package com.wowoniu.fendian.model;

import java.io.Serializable;
import java.util.Date;


/**
 * union_member
 * @author 
 */

public class UnionMember implements Serializable {
    /**
     * 会员表
     */
    private String id;

    /**
     * 会员id
     */
    private String userId;

    /**
     * 会员名
     */
    private String userName;

    /**
     * 会员头像
     */
    private String userImg;

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

    private String loginName;

    private Date createTime;

    private static final long serialVersionUID = 1L;

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
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

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }
}