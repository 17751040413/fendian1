package com.wowoniu.fendian.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * @author
 * @date
 */
@ApiModel("商品规格")
public class WaresSpec implements Serializable {

    @ApiModelProperty("主键ID")
    private String id;

    @ApiModelProperty("规格1")
    private String spec1;
    @ApiModelProperty("规格2")
    private String spec2;

    @ApiModelProperty("商品ID")
    private String waresId;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSpec1() {
        return spec1;
    }

    public void setSpec1(String spec1) {
        this.spec1 = spec1;
    }

    public String getSpec2() {
        return spec2;
    }

    public void setSpec2(String spec2) {
        this.spec2 = spec2;
    }

    public String getWaresId() {
        return waresId;
    }

    public void setWaresId(String waresId) {
        this.waresId = waresId;
    }
}
