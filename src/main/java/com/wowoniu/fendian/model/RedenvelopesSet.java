package com.wowoniu.fendian.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.Date;

/**
 * @author yuany
 * @date 2020-07-07
 */
@ApiModel("红包活动设置")
public class RedenvelopesSet implements Serializable {

    @ApiModelProperty("主键ID")
    private String id;

    @ApiModelProperty("标题")
    private String title;

    @ApiModelProperty("开始时间")
    private Date startTime;

    @ApiModelProperty("结束时间")
    private Date endTime;

    @ApiModelProperty("领取是否填写手机号（N：否；Y：是）")
    private String phoneEnable;

    @ApiModelProperty("新客专享（N：禁用；Y：启用【只允许从未下单客户领取】）")
    private String newCustomers;

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

    @ApiModelProperty("券开始时间")
    private Date couponStartTime;

    @ApiModelProperty("券结束时间")
    private Date couponEndTime;

    @ApiModelProperty("适用范围（默认【全店通用】）")
    private String range;

    @ApiModelProperty("奖励人数")
    private Integer rewardPeoples;

    @ApiModelProperty("奖励条件（每人分享券数量）")
    private Integer rewardCondition;

    @ApiModelProperty("活动总额（分）")
    private Integer totalActivities;

    @ApiModelProperty("活动描述")
    private String describe;

    @ApiModelProperty("图片URL（最多9张 ’；‘隔开）")
    private String pictureUrl;

    @ApiModelProperty("防刷（N：禁用；Y：启用）")
    private String preventBrush;

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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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

    public String getPhoneEnable() {
        return phoneEnable;
    }

    public void setPhoneEnable(String phoneEnable) {
        this.phoneEnable = phoneEnable;
    }

    public String getNewCustomers() {
        return newCustomers;
    }

    public void setNewCustomers(String newCustomers) {
        this.newCustomers = newCustomers;
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

    public Date getCouponStartTime() {
        return couponStartTime;
    }

    public void setCouponStartTime(Date couponStartTime) {
        this.couponStartTime = couponStartTime;
    }

    public Date getCouponEndTime() {
        return couponEndTime;
    }

    public void setCouponEndTime(Date couponEndTime) {
        this.couponEndTime = couponEndTime;
    }

    public String getRange() {
        return range;
    }

    public void setRange(String range) {
        this.range = range;
    }

    public Integer getRewardPeoples() {
        return rewardPeoples;
    }

    public void setRewardPeoples(Integer rewardPeoples) {
        this.rewardPeoples = rewardPeoples;
    }

    public Integer getRewardCondition() {
        return rewardCondition;
    }

    public void setRewardCondition(Integer rewardCondition) {
        this.rewardCondition = rewardCondition;
    }

    public Integer getTotalActivities() {
        return totalActivities;
    }

    public void setTotalActivities(Integer totalActivities) {
        this.totalActivities = totalActivities;
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
