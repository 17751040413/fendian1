package com.wowoniu.fendian.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * @author yuany
 * @date
 */
@ApiModel("商品规格详情")
public class WaresSpecDetail implements Serializable {

    @ApiModelProperty("主键ID")
    private String id;

    @ApiModelProperty("规格ID")
    private String specId;

    @ApiModelProperty("型号")
    private String model;

    @ApiModelProperty("价格（分）")
    private Integer price;

    @ApiModelProperty("库存（件）")
    private Integer stock;

    @ApiModelProperty("图片地址")
    private String pictureUrl;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public String getSpecId() {
        return specId;
    }

    public void setSpecId(String specId) {
        this.specId = specId;
    }

    public String getPictureUrl() {
        return pictureUrl;
    }

    public void setPictureUrl(String pictureUrl) {
        this.pictureUrl = pictureUrl;
    }
}
