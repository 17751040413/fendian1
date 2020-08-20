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

    @ApiModelProperty("状态（0：待付款；1：待发货；2：已发货；3：已完成；4：已关闭）")
    private String state;

    @ApiModelProperty("创建时间")
    private DateTime createTime;

    @ApiModelProperty("自提人名称")
    private String selfName;

    @ApiModelProperty("自提人手机号")
    private String selfPhone;

    @ApiModelProperty("购物车ID 多个，隔开")
    private String cartId;

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
}
