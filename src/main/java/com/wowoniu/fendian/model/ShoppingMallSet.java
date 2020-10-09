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

    @ApiModelProperty("运费设置类型 0店铺统一运费 1单独设置运费 2包邮")
    private String type;

    @ApiModelProperty("单独运费（分/单）")
    private int alone;

    @ApiModelProperty("状态（N:禁用；Y:启用）")
    private String state;

    @ApiModelProperty("自提（N:禁用；Y:启用）")
    private String selfRaising;

    @ApiModelProperty("配送（N:禁用；Y:启用）")
    private String distribution;

    @ApiModelProperty("默认运费（分/单）")
    private int freight;

    @ApiModelProperty("推荐（N:禁用；Y:启用）")
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

    public int getFreight() {
        return freight;
    }

    public void setFreight(int freight) {
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getAlone() {
        return alone;
    }

    public void setAlone(int alone) {
        this.alone = alone;
    }
}
