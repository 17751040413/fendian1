package com.wowoniu.fendian.model;

import java.io.Serializable;
import java.util.Date;


/**
 * shop_type
 * @author 
 */

public class ShopType implements Serializable {
    /**
     * 店铺类型id
     */
    private String id;

    /**
     * 类型名
     */
    private String name;

    /**
     * 行业id
     */
    private String industryId;

    /**
     * 创建人id
     */
    private String createBy;

    private Date createTime;

    private static final long serialVersionUID = 1L;

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

    public String getIndustryId() {
        return industryId;
    }

    public void setIndustryId(String industryId) {
        this.industryId = industryId;
    }

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
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