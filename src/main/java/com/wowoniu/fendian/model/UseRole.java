package com.wowoniu.fendian.model;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel
public class UseRole {

  @ApiModelProperty("id")
  private String id;
  @ApiModelProperty("角色标识")
  private String roleItem;
  @ApiModelProperty("角色名")
  private String roleName;
  @ApiModelProperty("角色图标")
  private String roleImg;
  @ApiModelProperty("创建时间")
  private java.util.Date createTime;


  public String getRoleImg() {
    return roleImg;
  }

  public void setRoleImg(String roleImg) {
    this.roleImg = roleImg;
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }


  public String getRoleItem() {
    return roleItem;
  }

  public void setRoleItem(String roleItem) {
    this.roleItem = roleItem;
  }


  public String getRoleName() {
    return roleName;
  }

  public void setRoleName(String roleName) {
    this.roleName = roleName;
  }


  public java.util.Date getCreateTime() {
    return createTime;
  }

  public void setCreateTime(java.util.Date createTime) {
    this.createTime = createTime;
  }

}
