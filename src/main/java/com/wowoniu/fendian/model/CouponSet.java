package com.wowoniu.fendian.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

/**
 * @author
 * @date 2020-07-07
 */
@ApiModel("优惠券设置")
public class CouponSet implements Serializable {

    @ApiModelProperty("主键ID")
    private String id;

    @ApiModelProperty("买单后送券（N：禁用；Y：启用【与新人专享互斥】）")
    private String orderCoupon;

    @ApiModelProperty("新客专享（N：禁用；Y：启用【只允许从未下单客户领取】）")
    private String newCustomers;

    @ApiModelProperty("领取是否填写手机号（N：否；Y：是）")
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

    @ApiModelProperty("券数量")
    private Integer couponNumber;

    @ApiModelProperty("活动描述")
    private String describe;

    @ApiModelProperty("图片URL（最多9张 ’；‘隔开）")
    private String pictureUrl;

    @ApiModelProperty("防刷（N：禁用；Y：启用）")
    private String preventBrush;

    @ApiModelProperty("背景音乐")
    private String musicUrl;

    @ApiModelProperty("商家ID")
    private String userId;

    @ApiModelProperty("今日浏览次数")
    private Integer todayBrowse;

    @ApiModelProperty("总浏览次数")
    private Integer browse;

    @ApiModelProperty("今日领券数")
    private Integer todayReceive;

    @ApiModelProperty("总领券数")
    private Integer receive;

    @ApiModelProperty("今日用券数")
    private Integer todayUse;

    @ApiModelProperty("总用券数")
    private Integer use;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getOrderCoupon() {
        return orderCoupon;
    }

    public void setOrderCoupon(String orderCoupon) {
        this.orderCoupon = orderCoupon;
    }

    public String getNewCustomers() {
        return newCustomers;
    }

    public void setNewCustomers(String newCustomers) {
        this.newCustomers = newCustomers;
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


    public Integer getCouponNumber() {
        return couponNumber;
    }

    public void setCouponNumber(Integer couponNumber) {
        this.couponNumber = couponNumber;
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

    public String getPreventBrush() {
        return preventBrush;
    }

    public void setPreventBrush(String preventBrush) {
        this.preventBrush = preventBrush;
    }

    public String getMusicUrl() {
        return musicUrl;
    }

    public void setMusicUrl(String musicUrl) {
        this.musicUrl = musicUrl;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Integer getTodayBrowse() {
        return todayBrowse;
    }

    public void setTodayBrowse(Integer todayBrowse) {
        this.todayBrowse = todayBrowse;
    }

    public Integer getBrowse() {
        return browse;
    }

    public void setBrowse(Integer browse) {
        this.browse = browse;
    }

    public Integer getTodayReceive() {
        return todayReceive;
    }

    public void setTodayReceive(Integer todayReceive) {
        this.todayReceive = todayReceive;
    }

    public Integer getReceive() {
        return receive;
    }

    public void setReceive(Integer receive) {
        this.receive = receive;
    }

    public Integer getTodayUse() {
        return todayUse;
    }

    public void setTodayUse(Integer todayUse) {
        this.todayUse = todayUse;
    }

    public Integer getUse() {
        return use;
    }

    public void setUse(Integer use) {
        this.use = use;
    }
}
