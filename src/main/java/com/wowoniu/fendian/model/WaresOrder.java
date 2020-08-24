package com.wowoniu.fendian.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.joda.time.DateTime;

import java.io.Serializable;

/**
 * @author yuany
 * @date 2020-07-04
 */
@ApiModel("商品订单")
public class WaresOrder implements Serializable {

    @ApiModelProperty("主键ID")
    private String id;

    @ApiModelProperty("用户ID")
    private String userId;

    @ApiModelProperty("商家ID")
    private String storeUserId;

    @ApiModelProperty("商品ID")
    private String waresId;

    @ApiModelProperty("规格ID")
    private String specDetailId;

    @ApiModelProperty("状态（0：待付款；1：已付款，2：待发货；3：已发货；4：已完成；5：已关闭）")
    private String state;

    @ApiModelProperty("创建时间")
    private DateTime createTime;

    @ApiModelProperty("自提人名称")
    private String selfName;

    @ApiModelProperty("自提人手机号")
    private String selfPhone;

    @ApiModelProperty("购物车选中结算货物的ID 多个，隔开")
    private String cartId;

    @ApiModelProperty("订单编号")
    private String orderCode;

    @ApiModelProperty("实付款")
    private String actualPayment;

    @ApiModelProperty("发货时间")
    private String sendTime;

    @ApiModelProperty("收货时间")
    private String sureTime;

    @ApiModelProperty("取货码")
    private String takeCode;

    @ApiModelProperty("快递单号")
    private String courierNumber;

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

    public String getStoreUserId() {
        return storeUserId;
    }

    public void setStoreUserId(String storeUserId) {
        this.storeUserId = storeUserId;
    }

    public String getWaresId() {
        return waresId;
    }

    public void setWaresId(String waresId) {
        this.waresId = waresId;
    }

    public String getSpecId() {
        return specDetailId;
    }

    public void setSpecId(String specId) {
        this.specDetailId = specId;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public DateTime getCreateTime() {
        return createTime;
    }

    public void setCreateTime(DateTime createTime) {
        this.createTime = createTime;
    }

    public String getSpecDetailId() {
        return specDetailId;
    }

    public void setSpecDetailId(String specDetailId) {
        this.specDetailId = specDetailId;
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

    public String getActualPayment() {
        return actualPayment;
    }

    public void setActualPayment(String actualPayment) {
        this.actualPayment = actualPayment;
    }

    public String getOrderCode() {
        return orderCode;
    }

    public void setOrderCode(String orderCode) {
        this.orderCode = orderCode;
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
}
