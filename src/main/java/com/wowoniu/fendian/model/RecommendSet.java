package com.wowoniu.fendian.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

/**
 * @author yuany
 * @date 2020-07-07
 */
@ApiModel("推荐活动设置")
public class RecommendSet implements Serializable {

    @ApiModelProperty("主键ID")
    private String id;

    @ApiModelProperty("标题")
    private String title;

    @ApiModelProperty("开始时间")
    private Timestamp startTime;

    @ApiModelProperty("结束时间")
    private Timestamp endTime;

    @ApiModelProperty("推荐是否填写手机号（N：否；Y：是）")
    private String phoneEnable;

    @ApiModelProperty("防刷（N：禁用；Y：启用）")
    private String preventBrush;

    @ApiModelProperty("推荐人-礼品类型（0：代金券；1：折扣券；2：兑换券）")
    private String recommendGiftType;

    @ApiModelProperty("推荐人-优惠金额（分）")
    private Integer recommendMoney;

    @ApiModelProperty("推荐人-使用门槛（分-满多少可用，0则无门槛）")
    private Integer recommendThreshold;

    @ApiModelProperty("推荐人-有效期类型(0:指定日期；1：有效天数）")
    private String recommendEffectiveType;

    @ApiModelProperty("推荐人-领取后有效天数（天）")
    private Integer recommendEffectiveDay;

    @ApiModelProperty("推荐人-开始时间")
    private Timestamp recommendStartTime;

    @ApiModelProperty("推荐人-结束时间")
    private Timestamp recommendEndTime;

    @ApiModelProperty("推荐人-适用范围（默认【全店通用】）")
    private String recommendRange;

    @ApiModelProperty("推荐人-礼品数量")
    private Integer recommendGiftNumber;

    @ApiModelProperty("推荐人-折扣（1位小数点）")
    private Double recommendDiscount;

    @ApiModelProperty("推荐人-兑换礼品名称")
    private String recommendGiftName;

    @ApiModelProperty("被推荐人-新客专享（N：禁用；Y：启用【只允许从未下单客户领取】）")
    private String recommendedNewCustomers;

    @ApiModelProperty("被推荐人-礼品类型（0：代金券；1：折扣券；2：兑换券）")
    private String recommendedGiftType;

    @ApiModelProperty("被推荐人-优惠金额（分）")
    private Integer recommendedMoney;

    @ApiModelProperty("被推荐人-使用门槛（分-满多少可用，0则无门槛）")
    private Integer recommendedThreshold;

    @ApiModelProperty("被推荐人-有效期类型(0:指定日期；1：有效天数）")
    private String recommendedEffectiveType;

    @ApiModelProperty("被推荐人-领取后有效天数（天）")
    private Integer recommendedEffectiveDay;

    @ApiModelProperty("被推荐人-开始时间")
    private Timestamp recommendedStartTime;

    @ApiModelProperty("被推荐人-结束时间")
    private Timestamp recommendedEndTime;

    @ApiModelProperty("被推荐人-适用范围（默认【全店通用】）")
    private String recommendedRange;

    @ApiModelProperty("被推荐人-礼品数量")
    private Integer recommendedGiftNumber;

    @ApiModelProperty("被推荐人-折扣（1位小数点）")
    private Double recommendedDiscount;

    @ApiModelProperty("被推荐人-兑换礼品名称")
    private String recommendedGiftName;

    @ApiModelProperty("活动描述")
    private String describe;

    @ApiModelProperty("图片URL（最多9张 ’；‘隔开）")
    private String pictureUrl;

    @ApiModelProperty("规则")
    private String rule;

    @ApiModelProperty("商家ID")
    private String userId;

    @ApiModelProperty("背景音乐")
    private String musicUrl;

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

    public String getPhoneEnable() {
        return phoneEnable;
    }

    public void setPhoneEnable(String phoneEnable) {
        this.phoneEnable = phoneEnable;
    }

    public String getPreventBrush() {
        return preventBrush;
    }

    public void setPreventBrush(String preventBrush) {
        this.preventBrush = preventBrush;
    }

    public String getRecommendGiftType() {
        return recommendGiftType;
    }

    public void setRecommendGiftType(String recommendGiftType) {
        this.recommendGiftType = recommendGiftType;
    }

    public Integer getRecommendMoney() {
        return recommendMoney;
    }

    public void setRecommendMoney(Integer recommendMoney) {
        this.recommendMoney = recommendMoney;
    }

    public Integer getRecommendThreshold() {
        return recommendThreshold;
    }

    public void setRecommendThreshold(Integer recommendThreshold) {
        this.recommendThreshold = recommendThreshold;
    }

    public String getRecommendEffectiveType() {
        return recommendEffectiveType;
    }

    public void setRecommendEffectiveType(String recommendEffectiveType) {
        this.recommendEffectiveType = recommendEffectiveType;
    }

    public Integer getRecommendEffectiveDay() {
        return recommendEffectiveDay;
    }

    public void setRecommendEffectiveDay(Integer recommendEffectiveDay) {
        this.recommendEffectiveDay = recommendEffectiveDay;
    }

    public Timestamp getRecommendStartTime() {
        return recommendStartTime;
    }

    public void setRecommendStartTime(Timestamp recommendStartTime) {
        this.recommendStartTime = recommendStartTime;
    }

    public Timestamp getRecommendEndTime() {
        return recommendEndTime;
    }

    public void setRecommendEndTime(Timestamp recommendEndTime) {
        this.recommendEndTime = recommendEndTime;
    }

    public String getRecommendRange() {
        return recommendRange;
    }

    public void setRecommendRange(String recommendRange) {
        this.recommendRange = recommendRange;
    }

    public Integer getRecommendGiftNumber() {
        return recommendGiftNumber;
    }

    public void setRecommendGiftNumber(Integer recommendGiftNumber) {
        this.recommendGiftNumber = recommendGiftNumber;
    }

    public Double getRecommendDiscount() {
        return recommendDiscount;
    }

    public void setRecommendDiscount(Double recommendDiscount) {
        this.recommendDiscount = recommendDiscount;
    }

    public String getRecommendGiftName() {
        return recommendGiftName;
    }

    public void setRecommendGiftName(String recommendGiftName) {
        this.recommendGiftName = recommendGiftName;
    }

    public String getRecommendedNewCustomers() {
        return recommendedNewCustomers;
    }

    public void setRecommendedNewCustomers(String recommendedNewCustomers) {
        this.recommendedNewCustomers = recommendedNewCustomers;
    }

    public String getRecommendedGiftType() {
        return recommendedGiftType;
    }

    public void setRecommendedGiftType(String recommendedGiftType) {
        this.recommendedGiftType = recommendedGiftType;
    }

    public Integer getRecommendedMoney() {
        return recommendedMoney;
    }

    public void setRecommendedMoney(Integer recommendedMoney) {
        this.recommendedMoney = recommendedMoney;
    }

    public Integer getRecommendedThreshold() {
        return recommendedThreshold;
    }

    public void setRecommendedThreshold(Integer recommendedThreshold) {
        this.recommendedThreshold = recommendedThreshold;
    }

    public String getRecommendedEffectiveType() {
        return recommendedEffectiveType;
    }

    public void setRecommendedEffectiveType(String recommendedEffectiveType) {
        this.recommendedEffectiveType = recommendedEffectiveType;
    }

    public Integer getRecommendedEffectiveDay() {
        return recommendedEffectiveDay;
    }

    public void setRecommendedEffectiveDay(Integer recommendedEffectiveDay) {
        this.recommendedEffectiveDay = recommendedEffectiveDay;
    }

    public Timestamp getRecommendedStartTime() {
        return recommendedStartTime;
    }

    public void setRecommendedStartTime(Timestamp recommendedStartTime) {
        this.recommendedStartTime = recommendedStartTime;
    }

    public Timestamp getRecommendedEndTime() {
        return recommendedEndTime;
    }

    public void setRecommendedEndTime(Timestamp recommendedEndTime) {
        this.recommendedEndTime = recommendedEndTime;
    }

    public String getRecommendedRange() {
        return recommendedRange;
    }

    public void setRecommendedRange(String recommendedRange) {
        this.recommendedRange = recommendedRange;
    }

    public Integer getRecommendedGiftNumber() {
        return recommendedGiftNumber;
    }

    public void setRecommendedGiftNumber(Integer recommendedGiftNumber) {
        this.recommendedGiftNumber = recommendedGiftNumber;
    }

    public Double getRecommendedDiscount() {
        return recommendedDiscount;
    }

    public void setRecommendedDiscount(Double recommendedDiscount) {
        this.recommendedDiscount = recommendedDiscount;
    }

    public String getRecommendedGiftName() {
        return recommendedGiftName;
    }

    public void setRecommendedGiftName(String recommendedGiftName) {
        this.recommendedGiftName = recommendedGiftName;
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

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getMusicUrl() {
        return musicUrl;
    }

    public void setMusicUrl(String musicUrl) {
        this.musicUrl = musicUrl;
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
