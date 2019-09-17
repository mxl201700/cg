package cn.itcast;

import com.xpand.starter.canal.annotation.EnableCanalClient;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * Package: cn.itcast
 * Author: Mxl
 * Date: Created in 2019/8/22 21:29
 **/

@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
@EnableEurekaClient
//启用canal
@EnableCanalClient
@EnableFeignClients(basePackages = {"cn.itcast.content.feign","cn.itcast.item.feign"})
public class CanalApplicatiion {
    public static void main(String[] args) {
        SpringApplication.run(CanalApplicatiion.class, args);
    }
}
