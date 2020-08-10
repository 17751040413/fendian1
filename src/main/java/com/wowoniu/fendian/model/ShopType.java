package com.wowoniu.fendian.model;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * shop_type
 * @author 
 */
@Data
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
}