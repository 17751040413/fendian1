package com.wowoniu.fendian.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * @author
 * @date 2020-07-04
 */
@ApiModel("商品订单")
public class WaresOrder implements Serializable {

    @ApiModelProperty("主键ID")
    private String id;

    @ApiModelProperty("商家ID")
    private String userId;

    @ApiModelProperty("买家ID")
    private String buyerId;

    @ApiModelProperty("头像地址")
    private String url;

    @ApiModelProperty("状态（0：待付款；1：已付款，2：待发货；3：已发货；4：已完成；5：已关闭）")
    private String state;

    @ApiModelProperty("创建时间")
    private Timestamp createTime;

    @ApiModelProperty("自提人名称")
    private String selfName;

    @ApiModelProperty("自提人手机号")
    private String selfPhone;

    @ApiModelProperty("购物车选中结算货物的ID 多个，隔开")
    private String cartId;

    @ApiModelProperty("优惠券ID")
    private String couponId;

    @ApiModelProperty("订单编号")
    private String orderCode;

    @ApiModelProperty("运费 ")
    private int freight;

    @ApiModelProperty("总额 ")
    private int price;

    @ApiModelProperty("优惠金额 ")
    private int discountAmount;

    @ApiModelProperty("实付款")
    private int actualPayment;

    @ApiModelProperty("发货时间")
    private String sendTime;

    @ApiModelProperty("收货时间")
    private String sureTime;

    @ApiModelProperty("取货码")
    private String takeCode;

    @ApiModelProperty("快递单号")
    private String courierNumber;

    @ApiModelProperty("配送方式（0:自提 1:快递）")
    private String deliveryMethod;

    @ApiModelProperty("收货地址ID ")
    private String addressId;

    @ApiModelProperty("收货地址 ")
    private String address;

    @ApiModelProperty("skey")
    private String skey;
    @ApiModelProperty("商品图片")
    private String wareUrl;
    @ApiModelProperty("商品数量")
    private int number;

    @ApiModelProperty("店铺名称")
    private String shopName;
    @ApiModelProperty("店铺LOGO")
    private String shopLogo;
    @ApiModelProperty("店铺地址")
    private String shopAddress;

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

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    public String getSelfName() {
        return selfName;
    }

    public void setSelfName(String selfName) {
        this.selfName = selfName;
    }

    public String getSelfPhone() {
        return selfPhone;
    }

    public void setSelfPhone(String selfPhone) {
        this.selfPhone = selfPhone;
    }

    public String getCartId() {
        return cartId;
    }

    public void setCartId(String cartId) {
        this.cartId = cartId;
    }

    public String getCouponId() {
        return couponId;
    }

    public void setCouponId(String couponId) {
        this.couponId = couponId;
    }

    public String getOrderCode() {
        return orderCode;
    }

    public void setOrderCode(String orderCode) {
        this.orderCode = orderCode;
    }

    public int getFreight() {
        return freight;
    }

    public void setFreight(int freight) {
        this.freight = freight;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getDiscountAmount() {
        return discountAmount;
    }

    public void setDiscountAmount(int discountAmount) {
        this.discountAmount = discountAmount;
    }

    public int getActualPayment() {
        return actualPayment;
    }

    public void setActualPayment(int actualPayment) {
        this.actualPayment = actualPayment;
    }

    public String getSendTime() {
        return sendTime;
    }

    public void setSendTime(String sendTime) {
        this.sendTime = sendTime;
    }

    public String getSureTime() {
        return sureTime;
    }

    public void setSureTime(String sureTime) {
        this.sureTime = sureTime;
    }

    public String getTakeCode() {
        return takeCode;
    }

    public void setTakeCode(String takeCode) {
        this.takeCode = takeCode;
    }

    public String getCourierNumber() {
        return courierNumber;
    }

    public void setCourierNumber(String courierNumber) {
        this.courierNumber = courierNumber;
    }

    public String getDeliveryMethod() {
        return deliveryMethod;
    }

    public void setDeliveryMethod(String deliveryMethod) {
        this.deliveryMethod = deliveryMethod;
    }

    public String getAddressId() {
        return addressId;
    }

    public void setAddressId(String addressId) {
        this.addressId = addressId;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getSkey() {
        return skey;
    }

    public void setSkey(String skey) {
        this.skey = skey;
    }

    public String getWareUrl() {
        return wareUrl;
    }

    public void setWareUrl(String wareUrl) {
        this.wareUrl = wareUrl;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
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

    public String getShopAddress() {
        return shopAddress;
    }

    public void setShopAddress(String shopAddress) {
        this.shopAddress = shopAddress;
    }
}
