package cn.itcast;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * Package: cn.itcast
 * Author: Mxl
 * Date: Created in 2019/8/26 15:24
 **/
@SpringBootApplication
@EnableEurekaClient
@EnableFeignClients(basePackages = "cn.itcast.search.feign")
public class SearchWebApplication {
    public static void main(String[] args) {
        SpringApplication.run(SearchWebApplication.class, args);
    }
}
