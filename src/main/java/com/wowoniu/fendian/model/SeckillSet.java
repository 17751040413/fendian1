package com.wowoniu.fendian.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * @author
 * @date 2020-07-07
 */
@ApiModel("秒杀活动设置")
public class SeckillSet implements Serializable {

    @ApiModelProperty("主键ID")
    private String id;

    @ApiModelProperty("头图URL")
    private String headPictureUrl;

    @ApiModelProperty("标题")
    private String title;

    @ApiModelProperty("开始时间")
    private Timestamp startTime;

    @ApiModelProperty("结束时间")
    private Timestamp endTime;

    @ApiModelProperty("秒杀是否填写手机号（N：否；Y：是）")
    private String phoneEnable;

    @ApiModelProperty("库存")
    private int stock;

    @ApiModelProperty("原价（分）")
    private int originalPrice;

    @ApiModelProperty("秒杀价格（分）")
    private int seckillPrice;

    @ApiModelProperty("单人秒杀次数（0和不填为不显示）")
    private int frequencyPersonal;

    @ApiModelProperty("支付类型（0：全额；1：预付）")
    private String paymentType;

    @ApiModelProperty("预付金额（分）")
    private int payAdvance;

    @ApiModelProperty("描述")
    private String describe;

    @ApiModelProperty("图片URL（最多九章’；‘隔开）")
    private String pictureUrl;

    @ApiModelProperty("规则")
    private String rule;

    @ApiModelProperty("防刷（N：禁用；Y：启用）")
    private String preventBrush;

    @ApiModelProperty("背景音乐")
    private String musicUrl;

    @ApiModelProperty("商家ID")
    private String userId;

    @ApiModelProperty("今日浏览次数")
    private int todayBrowse;

    @ApiModelProperty("总浏览次数")
    private int browse;

    @ApiModelProperty("今日领券数")
    private int todayReceive;

    @ApiModelProperty("总领券数")
    private int receive;

    @ApiModelProperty("今日用券数")
    private int todayUse;

    @ApiModelProperty("总用券数")
    private int use;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getHeadPictureUrl() {
        return headPictureUrl;
    }

    public void setHeadPictureUrl(String headPictureUrl) {
        this.headPictureUrl = headPictureUrl;
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

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public int getOriginalPrice() {
        return originalPrice;
    }

    public void setOriginalPrice(int originalPrice) {
        this.originalPrice = originalPrice;
    }

    public int getSeckillPrice() {
        return seckillPrice;
    }

    public void setSeckillPrice(int seckillPrice) {
        this.seckillPrice = seckillPrice;
    }

    public int getFrequencyPersonal() {
        return frequencyPersonal;
    }

    public void setFrequencyPersonal(int frequencyPersonal) {
        this.frequencyPersonal = frequencyPersonal;
    }

    public String getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(String paymentType) {
        this.paymentType = paymentType;
    }

    public int getPayAdvance() {
        return payAdvance;
    }

    public void setPayAdvance(int payAdvance) {
        this.payAdvance = payAdvance;
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

    public int getTodayReceive() {
        return todayReceive;
    }

    public void setTodayReceive(int todayReceive) {
        this.todayReceive = todayReceive;
    }

    public int getReceive() {
        return receive;
    }

    public void setReceive(int receive) {
        this.receive = receive;
    }

    public int getTodayUse() {
        return todayUse;
    }

    public void setTodayUse(int todayUse) {
        this.todayUse = todayUse;
    }

    public int getUse() {
        return use;
    }

    public void setUse(int use) {
        this.use = use;
    }
}
