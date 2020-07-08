package com.wowoniu.fendian.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class AliSmsConfig {
    @Value("${ali.access.key.id}")
    public String accessKeyId;
    @Value("${ali.access.key.secret}")
    public String accessKeySecret;
    @Value("${ali.temp.id}")
    public String tempId;

    public String signName = "喔喔牛";


}
