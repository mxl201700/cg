package cn.itcast.goods;

import entity.IdWorker;
import org.springframework.context.annotation.Bean;
import tk.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * Package: cn.itcast.goods
 * Author: Mxl
 * Date: Created in 2019/8/16 10:46
 **/
@SpringBootApplication
@EnableEurekaClient
@MapperScan(basePackages = {"cn.itcast.goods.dao"})
public class GoodsApplication {
    public static void main(String[] args) {
        SpringApplication.run(GoodsApplication.class, args);
    }

    @Bean
    public IdWorker idWorker(){
        return new IdWorker(0,0);
    }
}
