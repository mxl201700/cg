package com.changgou.token;

import org.junit.Test;
import org.springframework.security.jwt.Jwt;
import org.springframework.security.jwt.JwtHelper;
import org.springframework.security.jwt.crypto.sign.RsaVerifier;

/*****
 * @Author: shenkunlin
 * @Date: 2019/7/7 13:48
 * @Description: com.itcast.token
 *  使用公钥解密令牌数据
 ****/
public class ParseJwtTest {

    /***
     * 校验令牌
     */
    @Test
    public void testParseToken(){
        //令牌
        String token = "eyJhbGciOiJSUzI1NiIsInR5cCI6IkpXVCJ9.eyJzY29wZSI6WyJhcHAiXSwibmFtZSI6bnVsbCwiaWQiOm51bGwsImV4cCI6MTU2NzI2MzIyNCwiYXV0aG9yaXRpZXMiOlsic2Vja2lsbF9saXN0IiwiZ29vZHNfbGlzdCJdLCJqdGkiOiIyNjAzN2QxNS1kMGFiLTRmOTctYjNlNy04MjI2Nzc2ODNmMTUiLCJjbGllbnRfaWQiOiJjaGFuZ2dvdSIsInVzZXJuYW1lIjoic3ppdGhlaW1hIn0.gftdUlZltbdGKdRt9Urvw1Tu7sOvaZwiPCwBPMhaW1i_Ly0cfntuAB4CAJPw0dEH8azCWqv9obQHRK3nQ1Irdt0qZoTk1bfiK54QsUrWLOR0UfiqHTzikz_Jayxqk2fiHZqUv-IkxPoral1fgBCfRTbn2hZP86AS3ZfWcta5iyfsleBgqb0FKcFXIglyXHWCNc1lhV82IWdtJWJ2_jTnF34OIvXb1ZrMk-m392fchOOZp07DJbM9NUH39LL1gcMtX0ujH0DMKXp123S_O-3vO4Dk4aYEfvUthrQbYbPYx8qbcJWIFlbXeiMHzhlXLlsDMoQwbsRS0tYi5RzxVlHOzg";

        //公钥
        String publickey = "-----BEGIN PUBLIC KEY-----MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAoClzM+gDjEnl+pxALtoK1BpZn7WHayn5Zut2SacsJd1JcKtI1yLOJ0ThQOyfJYRBueL/yc8uEqD74OZi3OyGxwLDZpbUH4GIlfr9AvcCkz9XYzO0AhqFhCTZdwQEVZ2TsRgqEQko9ziqiZi225bXBxoRgpYfAKpgsAEqZp+GPXPBm+/1p0Gb6B75uyln1IbgDwnZlGMalwYpYFbfFmjnIkC1cKKmvHA3ljyMVw2bkLH2v0tlGe2b38CIcstGxPVDxQWpDVhHN0P9KkI4wSyDd+ialvbQlfl+nUutrGA+sBLxVEDdLATuaMzD7uME1EYkAPaAUuYV9LlcnB0JlbT32wIDAQAB-----END PUBLIC KEY-----";

        //校验Jwt
        Jwt jwt = JwtHelper.decodeAndVerify(token, new RsaVerifier(publickey));

        //获取Jwt原始内容
        String claims = jwt.getClaims();
        System.out.println(claims);
        //jwt令牌
        String encoded = jwt.getEncoded();
        System.out.println(encoded);
    }


}
