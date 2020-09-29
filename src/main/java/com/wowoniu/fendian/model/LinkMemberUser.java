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

    @ApiModelProperty("昵称")
    private String nickName;
    @ApiModelProperty("头像地址")
    private String url;
    @ApiModelProperty("创建时间")
    private String time;

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

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
