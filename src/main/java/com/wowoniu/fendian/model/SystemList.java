package com.wowoniu.fendian.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel
public class SystemList {

  private String id;
  @ApiModelProperty("系统名")
  private String name;
  @ApiModelProperty("系统标题")
  private String title;
  @ApiModelProperty("系统描述")
  private String describe;
  @ApiModelProperty("其他描述")
  private String other;
  @ApiModelProperty("价格")
  private Integer price;
  @ApiModelProperty("单个有效期")
  private Integer termOfValidity;
  @ApiModelProperty("是否有时效")
  private Integer timeFlg;
  @ApiModelProperty("创建时间")
  private java.util.Date createTime;


  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }


  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }


  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }


  public String getDescribe() {
    return describe;
  }

  public void setDescribe(String describe) {
    this.describe = describe;
  }


  public String getOther() {
    return other;
  }

  public void setOther(String other) {
    this.other = other;
  }


  public Integer getPrice() {
    return price;
  }

  public void setPrice(Integer price) {
    this.price = price;
  }


  public Integer getTermOfValidity() {
    return termOfValidity;
  }

  public void setTermOfValidity(Integer termOfValidity) {
    this.termOfValidity = termOfValidity;
  }


  public Integer getTimeFlg() {
    return timeFlg;
  }

  public void setTimeFlg(Integer timeFlg) {
    this.timeFlg = timeFlg;
  }


  public java.util.Date getCreateTime() {
    return createTime;
  }

  public void setCreateTime(java.util.Date createTime) {
    this.createTime = createTime;
  }

}
