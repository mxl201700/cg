package cn.itcast;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import tk.mybatis.spring.annotation.MapperScan;

/**
 * Package: cn.itcast
 * Author: Mxl
 * Date: Created in 2019/8/22 21:43
 **/
@SpringBootApplication
@EnableEurekaClient
@MapperScan(basePackages = {"cn.itcast.content.dao"})
public class ContentApplication {
    public static void main(String[] args) {
        SpringApplication.run(ContentApplication.class, args);
    }
}
