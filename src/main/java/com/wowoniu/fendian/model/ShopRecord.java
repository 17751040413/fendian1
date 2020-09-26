package com.wowoniu.fendian.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * 店铺浏览记录
 *
 * @author
 * @date 2020-09-14
 */
@ApiModel("店铺浏览记录")
public class ShopRecord implements Serializable {

    @ApiModelProperty("主键ID")
    private String id;
    @ApiModelProperty("商家ID")
    private String userId;
    @ApiModelProperty("买家ID")
    private String buyerId;
    @ApiModelProperty("店铺名称")
    private String shopName;
    @ApiModelProperty("店铺LOGO")
    private String shopLogo;
    @ApiModelProperty("最后一次进店时间")
    private Timestamp lastTime;

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

    public String getBuyerId() {
        return buyerId;
    }

    public void setBuyerId(String buyerId) {
        this.buyerId = buyerId;
    }

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    public String getShopLogo() {
        return shopLogo;
    }

    public void setShopLogo(String shopLogo) {
        this.shopLogo = shopLogo;
    }

    public Timestamp getLastTime() {
        return lastTime;
    }

    public void setLastTime(Timestamp lastTime) {
        this.lastTime = lastTime;
    }
}
