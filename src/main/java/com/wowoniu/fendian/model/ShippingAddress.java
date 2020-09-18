package com.wowoniu.fendian.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * 收货地址
 *
 * @author yuany
 * @date 2020-08-21
 */
@ApiModel("收货地址")
public class ShippingAddress implements Serializable {

    @ApiModelProperty("主键ID")
    private String id;
    @ApiModelProperty("买家ID")
    private String buyerId;
    @ApiModelProperty("姓名")
    private String name;
    @ApiModelProperty("手机号")
    private String phone;
    @ApiModelProperty("地址")
    private String address;
    @ApiModelProperty("默认地址（N;否 Y：是）")
    private String tab;

    @ApiModelProperty("skey")
    private String skey;

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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getTab() {
        return tab;
    }

    public void setTab(String tab) {
        this.tab = tab;
    }

    public String getSkey() {
        return skey;
    }

    public void setSkey(String skey) {
        this.skey = skey;
    }
}
