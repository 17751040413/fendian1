package com.wowoniu.fendian.model;

import java.io.Serializable;
import java.util.Date;

/**
 * free_charge_detail
 * @author 
 */

public class FreeChargeDetail implements Serializable {
    /**
     * 排队免单活动表
     */
    private String id;

    /**
     * 活动id
     */
    private String freeId;

    /**
     * 用户id
     */
    private String userId;

    /**
     * 用户名
     */
    private String userName;

    /**
     * 用户头像
     */
    private String headImg;

    /**
     * 号码
     */
    private Integer number;

    /**
     * 消费金额
     */
    private Double amount;

    /**
     * 免单状态 0-已取消 1-正在排队 2-已完成 3--已部分免单
     */
    private Integer freeType;

    /**
     * 已免金额
     */
    private Double yiMian;

    /**
     * 创建时间
     */
    private Date createTime;

    private String phone;

    private static final long serialVersionUID = 1L;


    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFreeId() {
        return freeId;
    }

    public void setFreeId(String freeId) {
        this.freeId = freeId;
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

    public String getHeadImg() {
        return headImg;
    }

    public void setHeadImg(String headImg) {
        this.headImg = headImg;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public Integer getFreeType() {
        return freeType;
    }

    public void setFreeType(Integer freeType) {
        this.freeType = freeType;
    }

    public Double getYiMian() {
        return yiMian;
    }

    public void setYiMian(Double yiMian) {
        this.yiMian = yiMian;
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