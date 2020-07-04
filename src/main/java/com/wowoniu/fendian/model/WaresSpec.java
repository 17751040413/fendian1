package com.wowoniu.fendian.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * @author yuany
 * @date
 */
@ApiModel("商品规格")
public class WaresSpec implements Serializable {

    @ApiModelProperty("主键ID")
    private String id;

    @ApiModelProperty("规格")
    private String spec;

    @ApiModelProperty("型号（多个’，‘隔开）")
    private String model;

    @ApiModelProperty("商品ID")
    private String waresId;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSpec() {
        return spec;
    }

    public void setSpec(String spec) {
        this.spec = spec;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getWaresId() {
        return waresId;
    }

    public void setWaresId(String waresId) {
        this.waresId = waresId;
    }
}
