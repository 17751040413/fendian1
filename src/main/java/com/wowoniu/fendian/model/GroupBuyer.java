package com.wowoniu.fendian.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * 拼团创建
 *
 * @author yuany
 * @date 2020-09-02
 */
@ApiModel("拼团创建")
public class GroupBuyer implements Serializable {

    @ApiModelProperty("主键ID")
    private String id;
    @ApiModelProperty("拼团ID")
    private String groupId;
    @ApiModelProperty("建团人ID")
    private String buyerId;
    @ApiModelProperty("拼团人ID“；”隔开")
    private String users;
    @ApiModelProperty("剩余可拼人数")
    private Integer number;
    @ApiModelProperty("创建时间")
    private Timestamp createTime;
    @ApiModelProperty("结束时间")
    private Timestamp endTime;
    @ApiModelProperty("状态：0拼团中；1成功；2失败")
    private Integer state;

    @ApiModelProperty("店铺名称")
    private String shopName;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public String getBuyerId() {
        return buyerId;
    }

    public void setBuyerId(String buyerId) {
        this.buyerId = buyerId;
    }

    public String getUsers() {
        return users;
    }

    public void setUsers(String users) {
        this.users = users;
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
}
