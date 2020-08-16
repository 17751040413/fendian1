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

    private Date createTime;

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