package cn.itcast.user.feign;

import cn.itcast.user.pojo.User;
import entity.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Package: cn.itcast.user.feign
 * Author: Mxl
 * Date: Created in 2019/9/1 14:55
 **/
@FeignClient(name="user")
@RequestMapping("/user")
public interface UserFeign {

    @GetMapping("/load/{id}")
    public Result<User> findByUsername(@PathVariable(name="id") String id);

    /**
     * 添加积分
     * @param points
     * @param username
     * @return
     */
    @GetMapping(value = "/points/add")
    public Result addPoints(@RequestParam(value="points") Integer points
            ,@RequestParam(value="username") String username );
}
