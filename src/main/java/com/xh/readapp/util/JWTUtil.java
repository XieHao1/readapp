package com.xh.readapp.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;

import java.util.Calendar;

public class JWTUtil {

    //token的密钥
    private final static String tokenSecret = "asFAsfasgagshdbAWFASVS";

    //token的过期时间（天）
    private static final int TIME = 1;

    /**
     * 生成token
     * @param userId 要在token中储存的用户的id
     * @return 生成的token
     */
    public String getToken(String userId){

        JWTCreator.Builder builder = JWT.create();

        //在payload中存储用户的id
        builder.withClaim("userId",userId);

        //设置过期时间和sigNature
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE,TIME);
        return builder.withExpiresAt(calendar.getTime()).sign(Algorithm.HMAC256(tokenSecret));
    }

    /**
     * 核实token是否正确
     * @param token token
     * @return 核实之后的Token对象
     */
    public DecodedJWT verify(String token){
        return JWT.require(Algorithm.HMAC256(tokenSecret)).build().verify(token);
    }
}
