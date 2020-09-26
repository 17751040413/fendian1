package com.wowoniu.fendian.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * @author
 * @date 2020-07-04
 */
@ApiModel("商城设置")
public class ShoppingMallSet implements Serializable {

    @ApiModelProperty("主键ID")
    private String id;

    @ApiModelProperty("商家ID")
    private String userId;

    @ApiModelProperty("状态（N:禁用；Y:启用）")
    private String state;

    @ApiModelProperty("自提（N:禁用；Y:启用）")
    private String selfRaising;

    @ApiModelProperty("配送（N:禁用；Y:启用）")
    private String distribution;

    @ApiModelProperty("默认运费（分/单）")
    private Integer freight;

    @ApiModelProperty("配送（N:禁用；Y:启用）")
    private String recommend;

    @ApiModelProperty("防刷（N：禁用；Y：启用）")
    private String preventBrush;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getSelfRaising() {
        return selfRaising;
    }

    public void setSelfRaising(String selfRaising) {
        this.selfRaising = selfRaising;
    }

    public String getDistribution() {
        return distribution;
    }

    public void setDistribution(String distribution) {
        this.distribution = distribution;
    }

    public Integer getFreight() {
        return freight;
    }

    public void setFreight(Integer freight) {
        this.freight = freight;
    }

    public String getRecommend() {
        return recommend;
    }

    public void setRecommend(String recommend) {
        this.recommend = recommend;
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
}
