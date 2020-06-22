package com.wowoniu.fendian.model;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
@ApiModel
public class UserSystem {

  private String id;
  @ApiModelProperty("用户id")
  private String userId;
  @ApiModelProperty("系统id")
  private String systemId;
  @ApiModelProperty("到期时间")
  private java.util.Date expireTime;


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


  public String getSystemId() {
    return systemId;
  }

  public void setSystemId(String systemId) {
    this.systemId = systemId;
  }


  public java.util.Date getExpireTime() {
    return expireTime;
  }

  public void setExpireTime(java.util.Date expireTime) {
    this.expireTime = expireTime;
  }

}
