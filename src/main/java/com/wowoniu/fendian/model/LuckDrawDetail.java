package com.wowoniu.fendian.model;

import io.swagger.annotations.ApiModelProperty;
import org.joda.time.DateTime;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * @author yuany
 * @date 2020-07-05
 */
public class LuckDrawDetail implements Serializable {

    @ApiModelProperty("主键ID")
    private String id;

    @ApiModelProperty("抽奖ID")
    private String luckDrawId;

    @ApiModelProperty("奖项名称")
    private String name;

    @ApiModelProperty("奖品类型（0：优惠券；1：自定义奖品）")
    private String type;

    @ApiModelProperty("优惠券类型（0：代金券；1：折扣券）")
    private String couponType;

    @ApiModelProperty("优惠金额（分）")
    private Integer preferential;

    @ApiModelProperty("优惠券满多少可用（分；默认0）")
    private Integer satisfy;

    @ApiModelProperty("使用范围")
    private String range;

    @ApiModelProperty("有效期类型（0：置顶日期；1：有效天数）")
    private String effectiveType;

    @ApiModelProperty("有效天数")
    private Integer effectiveDay;

    @ApiModelProperty("奖品总数量")
    private Integer prizeNumber;

    @ApiModelProperty("每日奖品数量")
    private Integer dayMax;

    @ApiModelProperty("券有效开始时间")
    private Timestamp startTime;

    @ApiModelProperty("券有效结束时间")
    private Timestamp endTime;

    @ApiModelProperty("折扣（两位小数）")
    private Double discount;

    @ApiModelProperty("奖品名称")
    private String prizeName;

    @ApiModelProperty("奖品图片")
    private String pictureUrl;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLuckDrawId() {
        return luckDrawId;
    }

    public void setLuckDrawId(String luckDrawId) {
        this.luckDrawId = luckDrawId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getCouponType() {
        return couponType;
    }

    public void setCouponType(String couponType) {
        this.couponType = couponType;
    }

    public Integer getPreferential() {
        return preferential;
    }

    public void setPreferential(Integer preferential) {
        this.preferential = preferential;
    }

    public Integer getSatisfy() {
        return satisfy;
    }

    public void setSatisfy(Integer satisfy) {
        this.satisfy = satisfy;
    }

    public String getRange() {
        return range;
    }

    public void setRange(String range) {
        this.range = range;
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

    public Integer getPrizeNumber() {
        return prizeNumber;
    }

    public void setPrizeNumber(Integer prizeNumber) {
        this.prizeNumber = prizeNumber;
    }

    public Integer getDayMax() {
        return dayMax;
    }

    public void setDayMax(Integer dayMax) {
        this.dayMax = dayMax;
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

    public Double getDiscount() {
        return discount;
    }

    public void setDiscount(Double discount) {
        this.discount = discount;
    }

    public String getPrizeName() {
        return prizeName;
    }

    public void setPrizeName(String prizeName) {
        this.prizeName = prizeName;
    }

    public String getPictureUrl() {
        return pictureUrl;
    }

    public void setPictureUrl(String pictureUrl) {
        this.pictureUrl = pictureUrl;
    }
}
