package com.wowoniu.fendian.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.joda.time.DateTime;

import java.io.Serializable;

/**
 * @author yuany
 * @date 2020-07-01
 */
@ApiModel("分销优惠券")
public class DistributionCoupon implements Serializable {

    @ApiModelProperty("主键ID")
    private String id;

    @ApiModelProperty("分销ID")
    private String distrbutionId;

    @ApiModelProperty("领取时是否填写手机号（N：否；Y：是）")
    private String phoneEnable;

    @ApiModelProperty("券类型(0:代金券；1：折扣券)")
    private String type;

    @ApiModelProperty("优惠金额（分）")
    private Integer money;

    @ApiModelProperty("折扣（1位小数点）")
    private Double discount;

    @ApiModelProperty("使用门槛（分-满多少可用，0则无门槛）")
    private Integer treshold;

    @ApiModelProperty("有效期类型(0:指定日期；1：有效天数）")
    private String effectiveType;

    @ApiModelProperty("领取后有效天数（天）")
    private Integer effectiveDay;

    @ApiModelProperty("开始时间")
    private DateTime startTime;

    @ApiModelProperty("结束时间")
    private DateTime endTime;

    @ApiModelProperty("适用范围（默认【全店通用】）")
    private String range;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDistrbutionId() {
        return distrbutionId;
    }

    public void setDistrbutionId(String distrbutionId) {
        this.distrbutionId = distrbutionId;
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

    public Integer getTreshold() {
        return treshold;
    }

    public void setTreshold(Integer treshold) {
        this.treshold = treshold;
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

    public DateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(DateTime startTime) {
        this.startTime = startTime;
    }

    public DateTime getEndTime() {
        return endTime;
    }

    public void setEndTime(DateTime endTime) {
        this.endTime = endTime;
    }

    public String getRange() {
        return range;
    }

    public void setRange(String range) {
        this.range = range;
    }
}
