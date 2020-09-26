package com.wowoniu.fendian.model;

import java.io.Serializable;
import java.util.Date;


/**
 * union_shop
 * @author 
 */

public class UnionShop implements Serializable {
    /**
     * 商圈店铺表
     */
    private String id;

    /**
     * 商圈id
     */
    private String unionId;

    /**
     * 商圈名
     */
    private String unionName;

    /**
     * 商圈logo
     */
    private String unionLogo;

    /**
     * 店铺名
     */
    private String shopName;

    /**
     * 店铺logo
     */
    private String shopLogo;

    /**
     * 店铺id
     */
    private String shopId;

    /**
     * 券类型
     */
    private int couponType;

    /**
     * 是否锁定
     */
    private int isLock;

    private int leaderWechatCount;

    private Date createTime;

    private static final long serialVersionUID = 1L;

    public int getIsLock() {
        return isLock;
    }

    public void setIsLock(int isLock) {
        this.isLock = isLock;
    }

    public int getLeaderWechatCount() {
        return leaderWechatCount;
    }

    public void setLeaderWechatCount(int leaderWechatCount) {
        this.leaderWechatCount = leaderWechatCount;
    }

    public int getCouponType() {
        return couponType;
    }

    public void setCouponType(int couponType) {
        this.couponType = couponType;
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

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    public String getShopLogo() {
        return shopLogo;
    }

    public void setShopLogo(String shopLogo) {
        this.shopLogo = shopLogo;
    }

    public String getShopId() {
        return shopId;
    }

    public void setShopId(String shopId) {
        this.shopId = shopId;
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