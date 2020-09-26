package com.wowoniu.fendian.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * 砍价创建
 *
 * @author
 * @date 2020-09-02
 */
@ApiModel("砍价创建")
public class BargainBuyer implements Serializable {
    @ApiModelProperty("主键ID")
    private String id;
    @ApiModelProperty("砍价ID")
    private String bargainId;
    @ApiModelProperty("创建ID")
    private String buyerId;
    @ApiModelProperty("助力人ID“；”隔开")
    private String users;
    @ApiModelProperty("剩余砍价人数")
    private Integer number;
    @ApiModelProperty("创建时间")
    private Timestamp createTime;
    @ApiModelProperty("结束时间")
    private Timestamp endTime;
    @ApiModelProperty("状态：0砍价中；1成功；2失败")
    private Integer state;
    @ApiModelProperty("店铺名称")
    private String shopName;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getBargainId() {
        return bargainId;
    }

    public void setBargainId(String bargainId) {
        this.bargainId = bargainId;
    }

    public String getBuyerId() {
        return buyerId;
    }

    public void setBuyerId(String buyerId) {
        this.buyerId = buyerId;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    public Timestamp getEndTime() {
        return endTime;
    }

    public void setEndTime(Timestamp endTime) {
        this.endTime = endTime;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }


    public String getUsers() {
        return users;
    }

    public void setUsers(String users) {
        this.users = users;
    }
}
