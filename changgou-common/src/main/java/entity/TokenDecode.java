package entity;

import com.alibaba.fastjson.JSON;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.jwt.Jwt;
import org.springframework.security.jwt.JwtHelper;
import org.springframework.security.jwt.crypto.sign.RsaVerifier;
import org.springframework.security.oauth2.provider.authentication.OAuth2AuthenticationDetails;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Package: cn.itcast.order.config
 * Author: Mxl
 * Date: Created in 2019/9/2 10:24
 **/
@Component
public class TokenDecode {

    private static final String PUBLIC_KEY = "public.key";

    public String getToken(){

        OAuth2AuthenticationDetails details = (OAuth2AuthenticationDetails) SecurityContextHolder.getContext().getAuthentication().getDetails();

        String tokenValue = details.getTokenValue();
        return tokenValue;
    }

    public Map<String,String> getUserInfo(){
        //获取当前登录的用户信息
        String token = getToken();
        //解析令牌
        String publicKey = getPublicKey();
        Jwt jwt = JwtHelper.decodeAndVerify(token, new RsaVerifier(publicKey));

        String claims = jwt.getClaims();

        System.out.println(claims);

        Map<String,String> map = JSON.parseObject(claims, Map.class);

        return map;


    }

    /**
     * 获得公钥
     * @return
     */
    public String getPublicKey(){
        Resource resource = new ClassPathResource(PUBLIC_KEY);
        try {
            InputStreamReader inputStreamReader = new InputStreamReader(resource.getInputStream());
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            return bufferedReader.lines().collect(Collectors.joining("\n"));
        } catch (IOException e) {

            return null;
        }
    }
}
