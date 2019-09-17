package cn.itcast.test;

import io.jsonwebtoken.*;
import org.junit.Test;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Package: cn.itcast.test
 * Author: Mxl
 * Date: Created in 2019/8/30 9:56
 **/
public class TestJwt {


    /**
     * 创建令牌
     */
    @Test
    public void createJwt(){

        long currentTimeMillis = System.currentTimeMillis();
        long i = currentTimeMillis+ 20000;
        JwtBuilder jwtBuilder = Jwts.builder();
                jwtBuilder.setId("888")//设置唯一编号
                          .setSubject("小红") //设置主题  可以是JSON数据
                          .setIssuedAt(new Date())//设置签发日期
                          .signWith(SignatureAlgorithm.HS256 ,"itcast" );//设置签名 使用HS256算法，并设置SecretKey(字符串)
        //构建 并返回一个字符串
        System.out.println(jwtBuilder.compact());
    }


    @Test
    public void testParseJwt(){

        String st ="eyJhbGciOiJIUzI1NiJ9.eyJqdGkiOiI4ODgiLCJzdWIiOiLlsI_nuqIiLCJpYXQiOjE1NjcxMzIxNTZ9.7gKabafRia_kr3e0ovyq06KAkPXT72vIK3lNXWofq8U";

        Jws<Claims> itcast = Jwts.parser()
                .setSigningKey("itcast")
                .parseClaimsJws(st);
        System.out.println(itcast.getBody());
    }

}
