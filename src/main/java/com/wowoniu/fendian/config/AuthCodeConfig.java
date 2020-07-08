package com.wowoniu.fendian.config;

import java.util.Date;

public class AuthCodeConfig {

    private String authcode;
    private String phone;
    private Date date;

    public AuthCodeConfig(String authcode, Date date, String phone) {
        this.authcode = authcode;
        this.date = date;
        this.phone = phone;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAuthcode() {
        return authcode;
    }

    public void setAuthcode(String authcode) {
        this.authcode = authcode;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
