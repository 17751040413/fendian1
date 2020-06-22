package com.wowoniu.fendian.model;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
@ApiModel
public class UserLogin {

  private String id;
  @ApiModelProperty("用户id")
  private String userId;
  @ApiModelProperty("设备码")
  private String identification;
  @ApiModelProperty("登录状态")
  private Integer loginType;
  @ApiModelProperty("登录时间")
  private java.util.Date loginTime;
  @ApiModelProperty("最后一次请求登录时间")
  private java.util.Date connectionTime;


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


  public String getIdentification() {
    return identification;
  }

  public void setIdentification(String identification) {
    this.identification = identification;
  }


  public Integer getLoginType() {
    return loginType;
  }

  public void setLoginType(Integer loginType) {
    this.loginType = loginType;
  }


  public java.util.Date getLoginTime() {
    return loginTime;
  }

  public void setLoginTime(java.util.Date loginTime) {
    this.loginTime = loginTime;
  }


  public java.util.Date getConnectionTime() {
    return connectionTime;
  }

  public void setConnectionTime(java.util.Date connectionTime) {
    this.connectionTime = connectionTime;
  }

}
