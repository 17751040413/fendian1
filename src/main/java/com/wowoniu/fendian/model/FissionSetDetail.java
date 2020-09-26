package com.wowoniu.fendian.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * @author
 * @date 2020-06-22
 */
@ApiModel("裂变设置详情")
public class FissionSetDetail implements Serializable {

    @ApiModelProperty("主键ID")
    private String id;

    @ApiModelProperty("裂变设置ID")
    private String fissionId;

    @ApiModelProperty("等级序列")
    private Integer level;

    @ApiModelProperty("等级名称")
    private String levelName;

    @ApiModelProperty("权益1：券类型（0：无；1：代金券；2：折扣券；3：兑换券）")
    private Integer couponType1;

    @ApiModelProperty("权益1：优惠金额（单位：分）")
    private double discountMoney1;

    @ApiModelProperty("权益1：使用门槛（单位：分）")
    private double useThreshold1;

    @ApiModelProperty("权益1：每月张数")
    private Integer monthNumber1;

    @ApiModelProperty("权益1：使用范围（默认【全店通用】）")
    private String useRange1;

    @ApiModelProperty("权益1：兑换券名称（不超过20个字）")
    private String exchangeCouponName1;

    @ApiModelProperty("权益1：折扣数")
    private double discount1;

    @ApiModelProperty("权益2：券类型（0：无；1：代金券；2：折扣券；3：兑换券）")
    private Integer couponType2;

    @ApiModelProperty("权益2：优惠金额（单位：分）")
    private double discountMoney2;

    @ApiModelProperty("权益2：使用门槛（单位：分）")
    private double useThreshold2;

    @ApiModelProperty("权益2：每月张数")
    private Integer monthNumber2;

    @ApiModelProperty("权益2：用范围（默认【全店通用】）")
    private String useRange2;

    @ApiModelProperty("权益2：兑换券名称（不超过20个字）")
    private String exchangeCouponName2;

    @ApiModelProperty("权益2：折扣数")
    private double discount2;

    @ApiModelProperty("获取条件")
    private String factor;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFissionId() {
        return fissionId;
    }

    public void setFissionId(String fissionId) {
        this.fissionId = fissionId;
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

    public Integer getCouponType1() {
        return couponType1;
    }

    public void setCouponType1(Integer couponType1) {
        this.couponType1 = couponType1;
    }

    public double getDiscountMoney1() {
        return discountMoney1;
    }

    public void setDiscountMoney1(double discountMoney1) {
        this.discountMoney1 = discountMoney1;
    }

    public double getUseThreshold1() {
        return useThreshold1;
    }

    public void setUseThreshold1(double useThreshold1) {
        this.useThreshold1 = useThreshold1;
    }

    public Integer getMonthNumber1() {
        return monthNumber1;
    }

    public void setMonthNumber1(Integer monthNumber1) {
        this.monthNumber1 = monthNumber1;
    }

    public String getUseRange1() {
        return useRange1;
    }

    public void setUseRange1(String useRang1) {
        this.useRange1 = useRang1;
    }

    public String getExchangeCouponName1() {
        return exchangeCouponName1;
    }

    public void setExchangeCouponName1(String exchangeCouponName1) {
        this.exchangeCouponName1 = exchangeCouponName1;
    }

    public double getDiscount1() {
        return discount1;
    }

    public void setDiscount1(double discount1) {
        this.discount1 = discount1;
    }

    public Integer getCouponType2() {
        return couponType2;
    }

    public void setCouponType2(Integer couponType2) {
        this.couponType2 = couponType2;
    }

    public double getDiscountMoney2() {
        return discountMoney2;
    }

    public void setDiscountMoney2(double discountMoney2) {
        this.discountMoney2 = discountMoney2;
    }

    public double getUseThreshold2() {
        return useThreshold2;
    }

    public void setUseThreshold2(double useThreshold2) {
        this.useThreshold2 = useThreshold2;
    }

    public Integer getMonthNumber2() {
        return monthNumber2;
    }

    public void setMonthNumber2(Integer monthNumber2) {
        this.monthNumber2 = monthNumber2;
    }

    public String getUseRange2() {
        return useRange2;
    }

    public void setUseRange2(String useRange2) {
        this.useRange2 = useRange2;
    }

    public String getExchangeCouponName2() {
        return exchangeCouponName2;
    }

    public void setExchangeCouponName2(String exchangeCouponName2) {
        this.exchangeCouponName2 = exchangeCouponName2;
    }

    public double getDiscount2() {
        return discount2;
    }

    public void setDiscount2(double discount2) {
        this.discount2 = discount2;
    }

    public String getFactor() {
        return factor;
    }

    public void setFactor(String factor) {
        this.factor = factor;
    }
}
