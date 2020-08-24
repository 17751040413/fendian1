package com.wowoniu.fendian.model;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * 买家领取的优惠券
 *
 * @author yuany
 * @date 2020-08-24
 */
public class CouponBuyer implements Serializable {

    @ApiModelProperty("主键ID")
    private String id;
    @ApiModelProperty("买家ID")
    private String buyerId;
    @ApiModelProperty("赠送人ID")
    private String donorId;
    @ApiModelProperty("商家ID")
    private String userId;
    @ApiModelProperty("开始时间")
    private String startTime;
    @ApiModelProperty("结束时间")
    private String endTime;
    @ApiModelProperty("领券时间")
    private String createTime;
    @ApiModelProperty("可用（Y:可用 N：过期）")
    private String effective;
    @ApiModelProperty("折扣")
    private Double discount;
    @ApiModelProperty("优惠金额（单位分）")
    private String discountAmount;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getBuyerId() {
        return buyerId;
    }

    public void setBuyerId(String buyerId) {
        this.buyerId = buyerId;
    }

    public String getDonorId() {
        return donorId;
    }

    public void setDonorId(String donorId) {
        this.donorId = donorId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getEffective() {
        return effective;
    }

    public void setEffective(String effective) {
        this.effective = effective;
    }

    public Double getDiscount() {
        return discount;
    }

    public void setDiscount(Double discount) {
        this.discount = discount;
    }

    public String getDiscountAmount() {
        return discountAmount;
    }

    public void setDiscountAmount(String discountAmount) {
        this.discountAmount = discountAmount;
    }
}
