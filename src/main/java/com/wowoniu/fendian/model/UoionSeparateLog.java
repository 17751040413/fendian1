package com.wowoniu.fendian.model;

import java.io.Serializable;
import java.util.Date;


/**
 * uoion_separate_log
 * @author 
 */

public class UoionSeparateLog implements Serializable {
    /**
     * 联盟分账记录表
     */
    private String id;

    /**
     * 订单id
     */
    private String orderId;

    /**
     * 分账金额
     */
    private Double sepPrice;

    /**
     * 分账人
     */
    private String userId;

    /**
     * 微信返回信息
     */
    private String data;

    private String unionId;

    /**
     * 是否入账
     */
    private int isEntry;

    /**
     * 分账日期
     */
    private Date createTime;

    private static final long serialVersionUID = 1L;

    public int getIsEntry() {
        return isEntry;
    }

    public void setIsEntry(int isEntry) {
        this.isEntry = isEntry;
    }

    public String getUnionId() {
        return unionId;
    }

    public void setUnionId(String unionId) {
        this.unionId = unionId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public Double getSepPrice() {
        return sepPrice;
    }

    public void setSepPrice(Double sepPrice) {
        this.sepPrice = sepPrice;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
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