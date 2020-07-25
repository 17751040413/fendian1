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
@ApiModel("砍价活动设置")
public class BargainingSet implements Serializable {

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

    @ApiModelProperty("砍价是否填写手机号（N：否；Y：是）")
    private String phoneEnable;

    @ApiModelProperty("库存")
    private Integer stock;

    @ApiModelProperty("原价（分）")
    private Integer originalPrice;

    @ApiModelProperty("低价（分）")
    private Integer floorPrice;

    @ApiModelProperty("砍价次数（多少人帮砍到底价）")
    private Integer bargainingFrequency;

    @ApiModelProperty("底价购买（N：关闭；Y：开启）")
    private String floorPriceEnable;

    @ApiModelProperty("支付类型（0：到店支付；1：预付定金；2：底价支付）")
    private String payType;

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

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public Integer getOriginalPrice() {
        return originalPrice;
    }

    public void setOriginalPrice(Integer originalPrice) {
        this.originalPrice = originalPrice;
    }

    public Integer getFloorPrice() {
        return floorPrice;
    }

    public void setFloorPrice(Integer floorPrice) {
        this.floorPrice = floorPrice;
    }

    public Integer getBargainingFrequency() {
        return bargainingFrequency;
    }

    public void setBargainingFrequency(Integer bargainingFrequency) {
        this.bargainingFrequency = bargainingFrequency;
    }

    public String getFloorPriceEnable() {
        return floorPriceEnable;
    }

    public void setFloorPriceEnable(String floorPriceEnable) {
        this.floorPriceEnable = floorPriceEnable;
    }

    public String getPayType() {
        return payType;
    }

    public void setPayType(String payType) {
        this.payType = payType;
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
