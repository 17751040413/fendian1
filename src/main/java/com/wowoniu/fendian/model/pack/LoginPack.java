package com.wowoniu.fendian.model.pack;

import com.wowoniu.fendian.model.UseUser;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel
public class LoginPack {

    @ApiModelProperty("用户信息")
    UseUser useUser;
    @ApiModelProperty("token")
    String token;
    @ApiModelProperty("绑定账号(1--绑定手机号 2--绑定微信号 0--不绑定)")
    int flg;

    public UseUser getUseUser() {
        return useUser;
    }

    public void setUseUser(UseUser useUser) {
        this.useUser = useUser;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public int getFlg() {
        return flg;
    }

    public void setFlg(int flg) {
        this.flg = flg;
    }
}
