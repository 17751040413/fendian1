package com.wowoniu.fendian.model;


/**
 * union_coupon_user
 * @author 
 */

public class UnionCouponUser {
    /**
     * 商圈优惠券领取表
     */
    private String id;

    /**
     * 小程序 用户id
     */
    private String userId;

    /**
     * 领取时间
     */
    private Integer receiveTime;

    /**
     * 联盟优惠券id
     */
    private String unionCouponId;

    /**
     * 核销表id
     */
    private String orderId;

    /**
     * 推广人id
     */
    private String extensionId;

    /**
     * 0未使用 1已使用
     */
    private Integer isUse;

    /**
     * 使用时间
     */
    private Integer isUseTime;

    /**
     * 过期时间
     */
    private Integer overdueTime;

    private static final long serialVersionUID = 1L;

    public String getExtensionId() {
        return extensionId;
    }

    public void setExtensionId(String extensionId) {
        this.extensionId = extensionId;
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

    public Integer getReceiveTime() {
        return receiveTime;
    }

    public void setReceiveTime(Integer receiveTime) {
        this.receiveTime = receiveTime;
    }

    public String getUnionCouponId() {
        return unionCouponId;
    }

    public void setUnionCouponId(String unionCouponId) {
        this.unionCouponId = unionCouponId;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public Integer getIsUse() {
        return isUse;
    }

    public void setIsUse(Integer isUse) {
        this.isUse = isUse;
    }

    public Integer getIsUseTime() {
        return isUseTime;
    }

    public void setIsUseTime(Integer isUseTime) {
        this.isUseTime = isUseTime;
    }

    public Integer getOverdueTime() {
        return overdueTime;
    }

    public void setOverdueTime(Integer overdueTime) {
        this.overdueTime = overdueTime;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }
}