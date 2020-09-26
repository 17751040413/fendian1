package com.wowoniu.fendian.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * 会员商家关联表
 *
 * @author
 * @date 2020-09-02
 */
@ApiModel("会员商家关联表")
public class LinkMemberUser implements Serializable {

    @ApiModelProperty("商家ID")
    private String userId;

    @ApiModelProperty("会员ID")
    private String buyId;

    @ApiModelProperty("邀请人ID")
    private String inviterId;

    /**
     * 买家用户实体
     */
    @ApiModelProperty("买家用户实体")
    private User user;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getBuyId() {
        return buyId;
    }

    public void setBuyId(String buyId) {
        this.buyId = buyId;
    }

    public String getInviterId() {
        return inviterId;
    }

    public void setInviterId(String inviterId) {
        this.inviterId = inviterId;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
