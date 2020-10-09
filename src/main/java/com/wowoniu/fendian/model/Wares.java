package com.wowoniu.fendian.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * @author
 * @date 2020-07-04
 */
@ApiModel("商品")
public class Wares implements Serializable {

    @ApiModelProperty("主键ID")
    private String id;

    @ApiModelProperty("商家ID")
    private String userId;

    @ApiModelProperty("标题")
    private String title;

    @ApiModelProperty("价格（分）")
    private Integer price;

    @ApiModelProperty("库存（件）")
    private Integer stock;

    @ApiModelProperty("运费（分）")
    private Integer freight;

    @ApiModelProperty("分类ID")
    private String sortId;

    @ApiModelProperty("分销佣金（N:禁用；Y：启用）")
    private String distributionCommission;

    @ApiModelProperty("隐藏（N:禁用；Y:启用）")
    private String hide;

    @ApiModelProperty("头图图片地址")
    private String pictureUrl;

    @ApiModelProperty("上架（Y：上架；N：下架）")
    private String onShelf;

    @ApiModelProperty("销量（件）")
    private Integer salesVolume;

    @ApiModelProperty("上架时间")
    private Timestamp shelfTime;

    @ApiModelProperty("描述")
    private String describe;

    @ApiModelProperty("图片URL（最多9张 ’；‘隔开）")
    private String url;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public Integer getFreight() {
        return freight;
    }

    public void setFreight(Integer freight) {
        this.freight = freight;
    }

    public String getSortId() {
        return sortId;
    }

    public void setSortId(String sortId) {
        this.sortId = sortId;
    }

    public String getDistributionCommission() {
        return distributionCommission;
    }

    public void setDistributionCommission(String distributionCommission) {
        this.distributionCommission = distributionCommission;
    }

    public String getHide() {
        return hide;
    }

    public void setHide(String hide) {
        this.hide = hide;
    }

    public String getPictureUrl() {
        return pictureUrl;
    }

    public void setPictureUrl(String pictureUrl) {
        this.pictureUrl = pictureUrl;
    }

    public String getOnShelf() {
        return onShelf;
    }

    public void setOnShelf(String onShelf) {
        this.onShelf = onShelf;
    }

    public Integer getSalesVolume() {
        return salesVolume;
    }

    public void setSalesVolume(Integer salesVolume) {
        this.salesVolume = salesVolume;
    }

    public Timestamp getShelfTime() {
        return shelfTime;
    }

    public void setShelfTime(Timestamp shelfTime) {
        this.shelfTime = shelfTime;
    }

    public String getDescribe() {
        return describe;
    }

    public void setDescribe(String describe) {
        this.describe = describe;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
