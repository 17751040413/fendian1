package com.wowoniu.fendian.model;

import java.io.Serializable;
import java.util.Date;


/**
 * union_coupon
 * @author 
 */

public class UnionCoupon implements Serializable {
    /**
     * 联盟优惠券
     */
    private String id;

    /**
     * 联盟id
     */
    private String unionId;

    /**
     * 联盟名
     */
    private String unionName;

    /**
     * 联盟logo
     */
    private String unionLogo;

    /**
     * 券类型 0--代金券 1--折扣券 2--兑换券
     */
    private Integer couponType;

    /**
     * 折扣 折扣 couponType为1必填
     */
    private Double discount;

    /**
     * 优惠金额 couponType为0必填
     */
    private Double disPrice;

    /**
     * 使用门槛 couponType为0必填
     */
    private Double disMan;

    /**
     * 礼物名 couponType为2必填
     */
    private String giftName;

    /**
     * 有效期类型 0--指定日期 1--有效天数
     */
    private Integer tremDateType;

    /**
     * 有效天数 tremDateType为1必填
     */
    private Integer termDate;

    /**
     * 开始日期 tremDateType为0必填
     */
    private Date startDate;

    /**
     * 结束日期 tremDateType为0必填
     */
    private Date endDate;

    /**
     * 每人限领张数
     */
    private Integer limitCount;

    /**
     * 使用范围
     */
    private String remarks;

    /**
     * 返佣方式 0--按比例 1--固定金额
     */
    private Integer yongType;

    /**
     * 比例 yongType为0必填
     */
    private Double yongPro;

    /**
     * 固定金额 yongType为1必填
     */
    private Double yongPrice;

    private int isState;

    /**
     * 创建时间
     */
    private Date createTime;

    private String shopId;

    private String shopName;

    public int getIsState() {
        return isState;
    }

    public void setIsState(int isState) {
        this.isState = isState;
    }

    public String getShopId() {
        return shopId;
    }

    public void setShopId(String shopId) {
        this.shopId = shopId;
    }

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    private static final long serialVersionUID = 1L;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUnionId() {
        return unionId;
    }

    public void setUnionId(String unionId) {
        this.unionId = unionId;
    }

    public String getUnionName() {
        return unionName;
    }

    public void setUnionName(String unionName) {
        this.unionName = unionName;
    }

    public String getUnionLogo() {
        return unionLogo;
    }

    public void setUnionLogo(String unionLogo) {
        this.unionLogo = unionLogo;
    }

    public Integer getCouponType() {
        return couponType;
    }

    public void setCouponType(Integer couponType) {
        this.couponType = couponType;
    }

    public Double getDiscount() {
        return discount;
    }

    public void setDiscount(Double discount) {
        this.discount = discount;
    }

    public Double getDisPrice() {
        return disPrice;
    }

    public void setDisPrice(Double disPrice) {
        this.disPrice = disPrice;
    }

    public Double getDisMan() {
        return disMan;
    }

    public void setDisMan(Double disMan) {
        this.disMan = disMan;
    }

    public String getGiftName() {
        return giftName;
    }

    public void setGiftName(String giftName) {
        this.giftName = giftName;
    }

    public Integer getTremDateType() {
        return tremDateType;
    }

    public void setTremDateType(Integer tremDateType) {
        this.tremDateType = tremDateType;
    }

    public Integer getTermDate() {
        return termDate;
    }

    public void setTermDate(Integer termDate) {
        this.termDate = termDate;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public Integer getLimitCount() {
        return limitCount;
    }

    public void setLimitCount(Integer limitCount) {
        this.limitCount = limitCount;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public Integer getYongType() {
        return yongType;
    }

    public void setYongType(Integer yongType) {
        this.yongType = yongType;
    }

    public Double getYongPro() {
        return yongPro;
    }

    public void setYongPro(Double yongPro) {
        this.yongPro = yongPro;
    }

    public Double getYongPrice() {
        return yongPrice;
    }

    public void setYongPrice(Double yongPrice) {
        this.yongPrice = yongPrice;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }
}