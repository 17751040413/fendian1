package com.wowoniu.fendian.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;


/**
 * write_off
 * @author 
 */

public class WriteOff implements Serializable {
    /**
     * 核销表
     */
    private String id;

    /**
     * 商店id/联盟id
     */
    private String userId;



    /**
     * 活动id
     */
    private String activityId;

    /**
     * 优惠券id
     */
    private String couponId;

    /**
     * 总价
     */
    private Long goodsAmount;

    /**
     * 实际支付价格
     */
    private Long payAmount;

    /**
     * 顾客id
     */
    private String customerId;

    /**
     * 创建时间
     */
    private Date createTime;
//----------------------------------------------------
    /**
     * 顾客昵称
     */
    private String customerName;

    /**
     * 顾客头像
     */
    private String customerImg;
    /**
     * 商店logo
     */
    private String userImg;

    private String userName;

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

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCustomerImg() {
        return customerImg;
    }

    public void setCustomerImg(String customerImg) {
        this.customerImg = customerImg;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
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

    public String getActivityId() {
        return activityId;
    }

    public void setActivityId(String activityId) {
        this.activityId = activityId;
    }

    public String getCouponId() {
        return couponId;
    }

    public void setCouponId(String couponId) {
        this.couponId = couponId;
    }

    public Long getGoodsAmount() {
        return goodsAmount;
    }

    public void setGoodsAmount(Long goodsAmount) {
        this.goodsAmount = goodsAmount;
    }

    public Long getPayAmount() {
        return payAmount;
    }

    public void setPayAmount(Long payAmount) {
        this.payAmount = payAmount;
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

    private static final long serialVersionUID = 1L;
}