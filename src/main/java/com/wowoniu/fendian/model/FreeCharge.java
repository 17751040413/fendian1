package com.wowoniu.fendian.model;

import java.io.Serializable;
import java.util.Date;


/**
 * free_charge
 * @author 
 */

public class FreeCharge implements Serializable {
    /**
     * 排队免单
     */
    private String id;

    /**
     * 标题
     */
    private String title;

    /**
     * 开始时间
     */
    private Date startTime;

    /**
     * 结束时间
     */
    private Date endTime;

    /**
     * 是否需要手机号 0不需要 1需要
     */
    private Integer ifPhone;

    /**
     * 每单让利
     */
    private Double profits;

    /**
     * 0-代金券 1-折扣券 2-兑换券
     */
    private Integer couponType;

    /**
     * 优惠金额 type为0必填
     */
    private double preAmount;

    /**
     * 使用门槛 type为0必填
     */
    private double useMan;

    /**
     * 折扣
     */
    private Double discount;

    /**
     * 礼物名称
     */
    private String giftName;

    /**
     * 有效期类型 0-指定日期 1-有效天数
     */
    private Integer dateType;

    /**
     *
     */
    private Date startDate;
    /**
     * 指定日期
     */
    private Date overdueDate;

    /**
     * 有效天数
     */
    private Integer dateNumber;

    /**
     * 发放张数
     */
    private Integer receliveNumber;

    /**
     * 适用范围
     */
    private String range;

    /**
     * 描述
     */
    private String describe;

    /**
     * 图片地址
     */
    private String pictureUrl;

    /**
     * 规则
     */
    private String rule;

    /**
     * 用户id
     */
    private String userId;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 浏览次数
     */
    private int browse;

    /**
     * 今日浏览次数
     */
    private int todayBrowse;

    /**
     * 活动状态
     */
    private int type;

    /**
     * 手机号
     */
    private String phone;

    private static final long serialVersionUID = 1L;


    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getTodayBrowse() {
        return todayBrowse;
    }

    public void setTodayBrowse(int todayBrowse) {
        this.todayBrowse = todayBrowse;
    }

    public int getBrowse() {
        return browse;
    }

    public void setBrowse(int browse) {
        this.browse = browse;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

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

    public Integer getIfPhone() {
        return ifPhone;
    }

    public void setIfPhone(Integer ifPhone) {
        this.ifPhone = ifPhone;
    }

    public Double getProfits() {
        return profits;
    }

    public void setProfits(Double profits) {
        this.profits = profits;
    }

    public Integer getCouponType() {
        return couponType;
    }

    public void setCouponType(Integer couponType) {
        this.couponType = couponType;
    }


    public double getPreAmount() {
        return preAmount;
    }

    public void setPreAmount(double preAmount) {
        this.preAmount = preAmount;
    }

    public double getUseMan() {
        return useMan;
    }

    public void setUseMan(double useMan) {
        this.useMan = useMan;
    }

    public void setUseMan(Long useMan) {
        this.useMan = useMan;
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

    public Integer getDateType() {
        return dateType;
    }

    public void setDateType(Integer dateType) {
        this.dateType = dateType;
    }

    public Date getOverdueDate() {
        return overdueDate;
    }

    public void setOverdueDate(Date overdueDate) {
        this.overdueDate = overdueDate;
    }

    public Integer getDateNumber() {
        return dateNumber;
    }

    public void setDateNumber(Integer dateNumber) {
        this.dateNumber = dateNumber;
    }

    public Integer getReceliveNumber() {
        return receliveNumber;
    }

    public void setReceliveNumber(Integer receliveNumber) {
        this.receliveNumber = receliveNumber;
    }

    public String getRange() {
        return range;
    }

    public void setRange(String range) {
        this.range = range;
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

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }
}