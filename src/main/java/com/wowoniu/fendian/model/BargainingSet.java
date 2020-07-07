package com.wowoniu.fendian.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
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
    private Date startTime;

    @ApiModelProperty("结束时间")
    private Date endTime;

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
    private Integer floorPriceEnable;

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

    public Integer getFloorPriceEnable() {
        return floorPriceEnable;
    }

    public void setFloorPriceEnable(Integer floorPriceEnable) {
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
}
