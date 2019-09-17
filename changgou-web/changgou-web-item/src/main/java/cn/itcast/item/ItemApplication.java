package cn.itcast.item;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * Package: cn.itcast.item
 * Author: Mxl
 * Date: Created in 2019/8/28 21:22
 **/
@SpringBootApplication
@EnableEurekaClient
@EnableFeignClients(basePackages = "cn.itcast.goods.feign")
public class ItemApplication {
    public static void main(String[] args) {
        SpringApplication.run(ItemApplication.class, args);
    }
}
