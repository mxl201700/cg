package cn.itcast;

import entity.TokenDecode;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import tk.mybatis.spring.annotation.MapperScan;

/**
 * Package: cn.itcast
 * Author: Mxl
 * Date: Created in 2019/8/29 19:07
 **/
@SpringBootApplication
@EnableEurekaClient
@MapperScan("cn.itcast.user.dao")
public class UserApplication {
    public static void main(String[] args) {
        SpringApplication.run(UserApplication.class, args);
    }


    @Bean
    public TokenDecode tokenDecode(){
        return new TokenDecode();
    }
}
