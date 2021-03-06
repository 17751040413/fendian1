package com.wowoniu.fendian.utils;

import com.wowoniu.fendian.model.UseUser;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;

public class JwtUtils {

    public static final String SUBJECT = "tzt";

    public static final long EXPIRE = 1000*60*60*24*7;//过期时间，毫秒，一周

    public static final String APPSECRET = "zt2020";//秘钥

    /**
     * 生成jwt
     * @param useUser
     * @return
     */
    public static String geneJsonToken(UseUser useUser){
        if (useUser == null){
            return null;
        }
        String token = Jwts.builder().setSubject(SUBJECT)
                .claim("sysid",useUser.getId())//加入id
                .claim("identification",useUser.getIdentification())//加入设备码
                .setIssuedAt(new Date())//设置发放时间
                .setExpiration(new Date(System.currentTimeMillis()+EXPIRE))//设置过期时间
                .signWith(SignatureAlgorithm.HS256,APPSECRET).compact();//设置签名(签名是自己设置 防止别人通过相同算法篡改)

        return token;
    }

    /**
     * 校验jwt
     * @param token
     * @return
     */
    public static Claims checkJWT(String token){

        try {
            final Claims claims = Jwts.parser().setSigningKey(APPSECRET).
                    parseClaimsJws(token).getBody();
            return claims;
        }catch (Exception e){
            return null;
        }
    }
}
