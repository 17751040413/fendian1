package com.wowoniu.fendian.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.Date;

/**
 * @author yuany
 * @date 2020-07-07
 */
@ApiModel("推荐活动设置详情")
public class RecommendDetail implements Serializable {

    @ApiModelProperty("主键ID")
    private String id;

    @ApiModelProperty("推荐ID")
    private String recommendId;

    @ApiModelProperty("礼品类型（0：代金券；1：折扣券；2：兑换券）")
    private String giftType;

    @ApiModelProperty("获取人（0：推荐人；1：被推荐人）")
    private String obtainPople;

    @ApiModelProperty("优惠金额（分）")
    private Integer money;

    @ApiModelProperty("使用门槛（分-满多少可用，0则无门槛）")
    private Integer threshold;

    @ApiModelProperty("有效期类型(0:指定日期；1：有效天数）")
    private String effectiveType;

    @ApiModelProperty("领取后有效天数（天）")
    private Integer effectiveDay;

    @ApiModelProperty("开始时间")
    private Date startTime;

    @ApiModelProperty("结束时间")
    private Date endTime;

    @ApiModelProperty("适用范围（默认【全店通用】）")
    private String range;

    @ApiModelProperty("礼品数量")
    private Integer giftNumber;

    @ApiModelProperty("折扣（1位小数点）")
    private Double discount;

    @ApiModelProperty("兑换礼品名称")
    private String giftName;

    @ApiModelProperty("新客专享（N：禁用；Y：启用【只允许从未下单客户领取】）")
    private String newCustomers;

    @ApiModelProperty("活动描述")
    private String describe;

    @ApiModelProperty("图片URL（最多9张 ’；‘隔开）")
    private String pictureUrl;

    @ApiModelProperty("规则")
    private String rule;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getRecommendId() {
        return recommendId;
    }

    public void setRecommendId(String recommendId) {
        this.recommendId = recommendId;
    }

    public String getGiftType() {
        return giftType;
    }

    public void setGiftType(String giftType) {
        this.giftType = giftType;
    }

    public String getObtainPople() {
        return obtainPople;
    }

    public void setObtainPople(String obtainPople) {
        this.obtainPople = obtainPople;
    }

    public Integer getMoney() {
        return money;
    }

    public void setMoney(Integer money) {
        this.money = money;
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

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public String getRange() {
        return range;
    }

    public void setRange(String range) {
        this.range = range;
    }

    public Integer getGiftNumber() {
        return giftNumber;
    }

    public void setGiftNumber(Integer giftNumber) {
        this.giftNumber = giftNumber;
    }

    public Double getDiscount() {
        return discount;
    }

    public void setDiscount(Double discount) {
        this.discount = discount;
    }

    public String getGiftName() {
        return giftName;
    }

    public void setGiftName(String giftName) {
        this.giftName = giftName;
    }

    public String getNewCustomers() {
        return newCustomers;
    }

    public void setNewCustomers(String newCustomers) {
        this.newCustomers = newCustomers;
    }

    public String getDescribe() {
        return describe;
    }

    public void setDescribe(String describe) {
        this.describe = describe;
    }

    public String getPictureUrl() {
        return pictureUrl;
    }

    public void setPictureUrl(String pictureUrl) {
        this.pictureUrl = pictureUrl;
    }

    public String getRule() {
        return rule;
    }

    public void setRule(String rule) {
        this.rule = rule;
    }
}
