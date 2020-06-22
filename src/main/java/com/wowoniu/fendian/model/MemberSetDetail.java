package com.wowoniu.fendian.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @author yuany
 * @date 2020-06-22
 */
@ApiModel("会员活动设置详情")
public class MemberSetDetail {

    @ApiModelProperty("主键ID")
    private String id;

    @ApiModelProperty("会员活动设置ID")
    private Integer memberSetId;

    @ApiModelProperty("等级序列")
    private Integer level;

    @ApiModelProperty("等级名称")
    private String levelName;

    @ApiModelProperty("每月权益（每个等级两个权益）")
    private Integer equity;

    @ApiModelProperty("券类型（0：无；1：代金券；2：折扣券；3：兑换券）")
    private Integer couponType;

    @ApiModelProperty("优惠金额（单位：分）")
    private double discountMoney;

    @ApiModelProperty("使用门槛（单位：分）")
    private double useThreshold;

    @ApiModelProperty("每月张数")
    private Integer monthNumber;

    @ApiModelProperty("使用范围（默认【全店通用】）")
    private String useRange;

    @ApiModelProperty("兑换券名称（不超过20个字）")
    private String exchangeCouponName;

    @ApiModelProperty("折扣数")
    private double discount;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }


    public Integer getMemberSetId() {
        return memberSetId;
    }

    public void setMemberSetId(Integer memberSetId) {
        this.memberSetId = memberSetId;
    }


    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }


    public String getLevelName() {
        return levelName;
    }

    public void setLevelName(String levelName) {
        this.levelName = levelName;
    }


    public Integer getEquity() {
        return equity;
    }

    public void setEquity(Integer equity) {
        this.equity = equity;
    }


    public Integer getCouponType() {
        return couponType;
    }

    public void setCouponType(Integer couponType) {
        this.couponType = couponType;
    }


    public double getDiscountMoney() {
        return discountMoney;
    }

    public void setDiscountMoney(double discountMoney) {
        this.discountMoney = discountMoney;
    }


    public double getUseThreshold() {
        return useThreshold;
    }

    public void setUseThreshold(double useThreshold) {
        this.useThreshold = useThreshold;
    }


    public Integer getMonthNumber() {
        return monthNumber;
    }

    public void setMonthNumber(Integer monthNumber) {
        this.monthNumber = monthNumber;
    }


    public String getUseRange() {
        return useRange;
    }

    public void setUseRange(String useRange) {
        this.useRange = useRange;
    }


    public String getExchangeCouponName() {
        return exchangeCouponName;
    }

    public void setExchangeCouponName(String exchangeCouponName) {
        this.exchangeCouponName = exchangeCouponName;
    }


    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }

}
