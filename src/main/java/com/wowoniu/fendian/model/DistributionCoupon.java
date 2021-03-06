package com.wowoniu.fendian.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * @author
 * @date 2020-07-01
 */
@ApiModel("分销优惠券")
public class DistributionCoupon implements Serializable {

    @ApiModelProperty("主键ID")
    private String id;

    @ApiModelProperty("分销ID")
    private String distributionId;

    @ApiModelProperty("新客专享（N：禁用；Y：启用）")
    private String newBuyer;

    @ApiModelProperty("领取时是否填写手机号（N：否；Y：是）")
    private String phoneEnable;

    @ApiModelProperty("券类型(0:代金券；1：折扣券)")
    private String type;

    @ApiModelProperty("优惠金额（分）")
    private Integer money;

    @ApiModelProperty("折扣（1位小数点）")
    private Double discount;

    @ApiModelProperty("使用门槛（分-满多少可用，0则无门槛）")
    private Integer threshold;

    @ApiModelProperty("有效期类型(0:指定日期；1：有效天数）")
    private String effectiveType;

    @ApiModelProperty("领取后有效天数（天）")
    private Integer effectiveDay;

    @ApiModelProperty("开始时间")
    private Timestamp startTime;

    @ApiModelProperty("结束时间")
    private Timestamp endTime;

    @ApiModelProperty("适用范围（默认【全店通用】）")
    private String range;

    @ApiModelProperty("领取数量")
    private int getNumber;

    @ApiModelProperty("使用数量")
    private Integer useNumber;

    @ApiModelProperty("券是否可用（N：禁用；Y：启用）")
    private String enable;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDistributionId() {
        return distributionId;
    }

    public void setDistributionId(String distributionId) {
        this.distributionId = distributionId;
    }

    public String getPhoneEnable() {
        return phoneEnable;
    }

    public void setPhoneEnable(String phoneEnable) {
        this.phoneEnable = phoneEnable;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getMoney() {
        return money;
    }

    public void setMoney(Integer money) {
        this.money = money;
    }

    public Double getDiscount() {
        return discount;
    }

    public void setDiscount(Double discount) {
        this.discount = discount;
    }

    public Integer getThreshold() {
        return threshold;
    }

    public void setThreshold(Integer threshold) {
        this.threshold = threshold;
    }

    public String getEffectiveType() {
        return effectiveType;
    }

    public void setEffectiveType(String effectiveType) {
        this.effectiveType = effectiveType;
    }

    public Integer getEffectiveDay() {
        return effectiveDay;
    }

    public void setEffectiveDay(Integer effectiveDay) {
        this.effectiveDay = effectiveDay;
    }

    public Timestamp getStartTime() {
        return startTime;
    }

    public void setStartTime(Timestamp startTime) {
        this.startTime = startTime;
    }

    public Timestamp getEndTime() {
        return endTime;
    }

    public void setEndTime(Timestamp endTime) {
        this.endTime = endTime;
    }

    public String getRange() {
        return range;
    }

    public void setRange(String range) {
        this.range = range;
    }

    public String getNewBuyer() {
        return newBuyer;
    }

    public void setNewBuyer(String newBuyer) {
        this.newBuyer = newBuyer;
    }

    public int getGetNumber() {
        return getNumber;
    }

    public void setGetNumber(int getNumber) {
        this.getNumber = getNumber;
    }

    public Integer getUseNumber() {
        return useNumber;
    }

    public void setUseNumber(Integer useNumber) {
        this.useNumber = useNumber;
    }

    public String getEnable() {
        return enable;
    }

    public void setEnable(String enable) {
        this.enable = enable;
    }
}
