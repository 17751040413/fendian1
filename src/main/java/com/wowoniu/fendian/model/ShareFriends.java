package com.wowoniu.fendian.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * @author yuany
 * @date 2020-07-07
 */
@ApiModel("分享朋友圈")
public class ShareFriends implements Serializable {

    @ApiModelProperty("主键ID")
    private String id;

    @ApiModelProperty("商家ID")
    private String userId;

    @ApiModelProperty("文案")
    private String copywriting;

    @ApiModelProperty("图片URL（最多9张’；‘隔开）")
    private String pictureUrl;

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

    public String getCopywriting() {
        return copywriting;
    }

    public void setCopywriting(String copywriting) {
        this.copywriting = copywriting;
    }

    public String getPictureUrl() {
        return pictureUrl;
    }

    public void setPictureUrl(String pictureUrl) {
        this.pictureUrl = pictureUrl;
    }
}
