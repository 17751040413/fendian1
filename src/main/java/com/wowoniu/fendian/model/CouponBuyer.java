package com.wowoniu.fendian.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * 买家领取的优惠券
 *
 * @author
 * @date 2020-08-24
 */
@ApiModel("买家领取的优惠券")
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
    private Timestamp startTime;
    @ApiModelProperty("结束时间")
    private Timestamp endTime;
    @ApiModelProperty("领券时间")
    private Timestamp createTime;
    @ApiModelProperty("消费门槛")
    private String condition;
    @ApiModelProperty("使用范围")
    private String range;
    @ApiModelProperty("兑换数量")
    private int exchangeNumber;
    @ApiModelProperty("兑换内容 / 奖品名称")
    private String exchangeContent;
    @ApiModelProperty("原价")
    private int price;
    @ApiModelProperty("活动价")
    private int activityPrice;
    @ApiModelProperty("已支付")
    private int payPrice;
    @ApiModelProperty("可用（Y:未使用 N：已使用）")
    private String effective;
    @ApiModelProperty("券类型（0：折扣 1：优惠 2：兑换）")
    private String type;
    @ApiModelProperty("折扣")
    private Double discount;
    @ApiModelProperty("优惠金额（单位分）")
    private int discountAmount;
    @ApiModelProperty(" 0：会员裂变；1：会员返利；2：店铺分销；3：在线商城；4：幸运转盘；5：发优惠券；6：推荐有礼；7：秒杀活动；8：拼团活动；9：砸金蛋抽奖；10：砍价大战；11：红包裂变券；12：朋友圈 13:联盟 14:排队")
    private String activityType;
    @ApiModelProperty("活动ID")
    private String activityId;
    @ApiModelProperty("活动名称")
    private String activityName;
    @ApiModelProperty("用户上传的活动图片")
    private String activityUrl;

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

    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    public String getCondition() {
        return condition;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }

    public String getRange() {
        return range;
    }

    public void setRange(String range) {
        this.range = range;
    }

    public int getExchangeNumber() {
        return exchangeNumber;
    }

    public void setExchangeNumber(int exchangeNumber) {
        this.exchangeNumber = exchangeNumber;
    }

    public String getExchangeContent() {
        return exchangeContent;
    }

    public void setExchangeContent(String exchangeContent) {
        this.exchangeContent = exchangeContent;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getActivityPrice() {
        return activityPrice;
    }

    public void setActivityPrice(int activityPrice) {
        this.activityPrice = activityPrice;
    }

    public int getPayPrice() {
        return payPrice;
    }

    public void setPayPrice(int payPrice) {
        this.payPrice = payPrice;
    }

    public String getEffective() {
        return effective;
    }

    public void setEffective(String effective) {
        this.effective = effective;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Double getDiscount() {
        return discount;
    }

    public void setDiscount(Double discount) {
        this.discount = discount;
    }

    public int getDiscountAmount() {
        return discountAmount;
    }

    public void setDiscountAmount(int discountAmount) {
        this.discountAmount = discountAmount;
    }

    public String getActivityType() {
        return activityType;
    }

    public void setActivityType(String activityType) {
        this.activityType = activityType;
    }

    public String getActivityId() {
        return activityId;
    }

    public void setActivityId(String activityId) {
        this.activityId = activityId;
    }

    public String getActivityName() {
        return activityName;
    }

    public void setActivityName(String activityName) {
        this.activityName = activityName;
    }

    public String getActivityUrl() {
        return activityUrl;
    }

    public void setActivityUrl(String activityUrl) {
        this.activityUrl = activityUrl;
    }
}
