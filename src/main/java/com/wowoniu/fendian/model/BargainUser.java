package com.wowoniu.fendian.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

@ApiModel("砍价参与人")
public class BargainUser implements Serializable {
    @ApiModelProperty("主键ID")
    private String id;
    @ApiModelProperty("买家ID")
    private String buyerId;
    @ApiModelProperty("名称")
    private String name;
    @ApiModelProperty("头像")
    private String url;
    @ApiModelProperty("金额")
    private Integer price;
    @ApiModelProperty("活动ID")
    private String bargainId;

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public String getBargainId() {
        return bargainId;
    }

    public void setBargainId(String bargainId) {
        this.bargainId = bargainId;
    }
}
