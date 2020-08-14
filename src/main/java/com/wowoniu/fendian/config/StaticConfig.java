package com.wowoniu.fendian.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;

/**
 * 静态方法获取配置文件信息
 *
 * @author yuany
 * @date 2020-08-05
 */
@Configuration
public class StaticConfig {

    private static String shop_range;

    /**
     * 附近商铺的获取范围（KM）
     */
    @Value("${shop.range}")
    private String getShopRange;

    @PostConstruct
    public void getApiToken() {
        shop_range = this.getShopRange;
    }

    public static String getShopRange() {
        return shop_range;
    }

}
