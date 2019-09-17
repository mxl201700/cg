package cn.itcast.order;

import cn.itcast.order.config.MyFeignInterceptor;
import cn.itcast.user.feign.UserFeign;
import cn.itcast.user.pojo.User;
import entity.IdWorker;
import entity.Result;
import entity.TokenDecode;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import tk.mybatis.spring.annotation.MapperScan;

/**
 * Package: cn.itcast.order
 * Author: Mxl
 * Date: Created in 2019/9/1 16:15
 **/
@SpringBootApplication
@EnableEurekaClient
@MapperScan(basePackages = "cn.itcast.order.dao")
@EnableFeignClients(basePackages = {"cn.itcast.goods.feign","cn.itcast.user.feign"})
public class OrderApplication {
    public static void main(String[] args) {
        SpringApplication.run(OrderApplication.class, args);
    }

    @Bean
    public TokenDecode tokenDecode(){
        return new TokenDecode();
    }

//    @Bean
//    public MyFeignInterceptor myFeignInterceptor(){
//        return new MyFeignInterceptor();
//    }

    @Bean
    public IdWorker idWorker(){
        return new IdWorker(0,1);
    }

}
