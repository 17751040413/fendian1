package com.wowoniu.fendian.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.Date;

/**
 * @author yuany
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
    private Date startTime;

    @ApiModelProperty("结束时间")
    private Date endTime;

    @ApiModelProperty("秒杀是否填写手机号（N：否；Y：是）")
    private String phoneEnable;

    @ApiModelProperty("库存")
    private Integer stock;

    @ApiModelProperty("原价（分）")
    private Integer originalPrice;

    @ApiModelProperty("秒杀价格（分）")
    private Integer sekillPrice;

    @ApiModelProperty("单人秒杀次数（0和不填为不显示）")
    private Integer frequencyPersonal;

    @ApiModelProperty("支付类型（0：全额；1：预付）")
    private String paymentType;

    @ApiModelProperty("预付金额（分）")
    private Integer pay_advance;

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

    public Integer getSekillPrice() {
        return sekillPrice;
    }

    public void setSekillPrice(Integer sekillPrice) {
        this.sekillPrice = sekillPrice;
    }

    public Integer getFrequencyPersonal() {
        return frequencyPersonal;
    }

    public void setFrequencyPersonal(Integer frequencyPersonal) {
        this.frequencyPersonal = frequencyPersonal;
    }

    public String getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(String paymentType) {
        this.paymentType = paymentType;
    }

    public Integer getPay_advance() {
        return pay_advance;
    }

    public void setPay_advance(Integer pay_advance) {
        this.pay_advance = pay_advance;
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
