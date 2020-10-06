package com.wowoniu.fendian.model.pack;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel
public class UserInfoPack {
    @ApiModelProperty("头像地址")
    String headImg;
    @ApiModelProperty("推荐人")
    String parentName;
    @ApiModelProperty("推广收益")
    double promotionIncome;
    @ApiModelProperty("余额")
    double balance;
    @ApiModelProperty("角色id")
    String roleId;
    @ApiModelProperty("角色名")
    String roleName;
    @ApiModelProperty("id")
    String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getHeadImg() {
        return headImg;
    }

    public void setHeadImg(String headImg) {
        this.headImg = headImg;
    }

    public String getParentName() {
        return parentName;
    }

    public void setParentName(String parentName) {
        this.parentName = parentName;
    }

    public double getPromotionIncome() {
        return promotionIncome;
    }

    public void setPromotionIncome(double promotionIncome) {
        this.promotionIncome = promotionIncome;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }


}
