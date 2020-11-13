package com.neu.auth_common;


import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.joda.time.DateTime;

import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.Calendar;

/**
 * JwtUtil
 */
public class JWTUtil {





    /**
     * 私钥生成Token
     * @param oriInfo
     * @param privateKey
     * @param expire      过期时间,单位秒
     * @return
     * @throws Exception
     */


    public static String generateToken(String oriInfo, PrivateKey privateKey, int expire)  {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.HOUR_OF_DAY,1);
        return Jwts.builder()
                .claim("info",oriInfo)
                .setExpiration(calendar.getTime())
                .signWith(SignatureAlgorithm.RS256,privateKey)
                .compact();
    }

    /**
     * 从token中获取原始信息
     * @param token
     * @param publicKey
     * @return
     * @throws Exception
     */
    public static String getInfoFromToken(String token, PublicKey publicKey)  {
        Jws<Claims> claimsJws = Jwts.parser().setSigningKey(publicKey).parseClaimsJws(token);
        Claims body = claimsJws.getBody();
        return body.get("info")+"";
    }

    public static void main(String ... args) throws Exception {

        RSAUtil.generateKey("D://pub.rsa","D://pri.rsa","abc");
        PrivateKey privateKey =RSAUtil.getPrivateKey("D://pri.rsa");
        PublicKey publicKey =RSAUtil.getPublicKey("D://pub.rsa");
        String token = generateToken("xx",privateKey,1000);

        String tokenInfo = getInfoFromToken(token,publicKey);
        System.out.println(tokenInfo);


    }

}