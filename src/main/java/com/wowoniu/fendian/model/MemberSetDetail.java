package com.wowoniu.fendian.model;


public class MemberSetDetail {

  private String id;
  private Integer memberSetId;
  private Integer level;
  private String levelName;
  private Integer equity;
  private Integer couponType;
  private double discountMoney;
  private double useThreshold;
  private Integer monthNumber;
  private String useRange;
  private String exchangeCouponName;
  private double discount;


  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }


  public Integer getMemberSetId() {
    return memberSetId;
  }

  public void setMemberSetId(Integer memberSetId) {
    this.memberSetId = memberSetId;
  }


  public Integer getLevel() {
    return level;
  }

  public void setLevel(Integer level) {
    this.level = level;
  }


  public String getLevelName() {
    return levelName;
  }

  public void setLevelName(String levelName) {
    this.levelName = levelName;
  }


  public Integer getEquity() {
    return equity;
  }

  public void setEquity(Integer equity) {
    this.equity = equity;
  }


  public Integer getCouponType() {
    return couponType;
  }

  public void setCouponType(Integer couponType) {
    this.couponType = couponType;
  }


  public double getDiscountMoney() {
    return discountMoney;
  }

  public void setDiscountMoney(double discountMoney) {
    this.discountMoney = discountMoney;
  }


  public double getUseThreshold() {
    return useThreshold;
  }

  public void setUseThreshold(double useThreshold) {
    this.useThreshold = useThreshold;
  }


  public Integer getMonthNumber() {
    return monthNumber;
  }

  public void setMonthNumber(Integer monthNumber) {
    this.monthNumber = monthNumber;
  }


  public String getUseRange() {
    return useRange;
  }

  public void setUseRange(String useRange) {
    this.useRange = useRange;
  }


  public String getExchangeCouponName() {
    return exchangeCouponName;
  }

  public void setExchangeCouponName(String exchangeCouponName) {
    this.exchangeCouponName = exchangeCouponName;
  }


  public double getDiscount() {
    return discount;
  }

  public void setDiscount(double discount) {
    this.discount = discount;
  }

}
